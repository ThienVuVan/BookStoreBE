package com.bookstore.modules.book.api;

import com.bookstore.common.entity.*;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.*;
import com.bookstore.modules.book.dto.*;
import com.bookstore.modules.book.mapper.BookImageMapper;
import com.bookstore.modules.book.request.*;
import com.bookstore.modules.book.service.BookModuleService;
import com.bookstore.modules.category.dto.CategoryDto;
import com.bookstore.modules.category.service.CategoryModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookDetailsService bookDetailsService;
    private final AuthorService authorService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final BookImageService bookImageService;
    private final BookImageMapper bookImageMapper;
    private final BookModuleService bookModuleService;
    private final CategoryModuleService categoryModuleService;
    private final ShopService shopService;

    @GetMapping(value = {Uri.BOOKS_PAGE})
    public ResponseEntity<?> RetrieveBooksByPage(@RequestParam Integer page){
        Pageable pageRequest = PageRequest.of(page, 5);
        List<BookDto> bookDtos = bookModuleService.convertPageToListBookDto(
                bookService.retrieveBooksByPage(pageRequest));
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @GetMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> RetrieveBookById(@RequestParam Integer bookId){
        BookDto bookDto = bookModuleService.convertToBookDto(bookService.retrieveById(bookId));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping(value = {Uri.BOOKS_FILTER})
    public ResponseEntity<?> RetrieveBookByCondition(@Valid @RequestBody BookSearchRequest bookSearchRequest){
        List<BookDto> bookDtos = bookModuleService.convertToListBookDto(
                bookService.retrieveBookByCondition(
                        bookSearchRequest.getTitle(),
                        bookSearchRequest.getPrice(),
                        bookSearchRequest.getCategory()
                ));
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @PostMapping(value = {Uri.BOOKS}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> CreateNewBook(@RequestParam Integer shopId,
                                           @Valid @RequestBody BookRequest bookRequest,
                                           @Valid @RequestBody BookDetailsRequest bookDetailsRequest,
                                           @RequestBody List<String> listAuthor,
                                           @RequestBody List<String> listCategory,
                                           @RequestBody List<MultipartFile> listImage){
        Shop shop = shopService.retrieveShopById(shopId);
        // save book;
        Book book = bookService.saveBook(new Book(bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getCurrentQuantity()));
        // save bookDetails;
        BookDetails bookDetails = bookDetailsService.saveBookDetails(new BookDetails(
                bookDetailsRequest.getPublisher(),
                bookDetailsRequest.getPublicationDate(),
                bookDetailsRequest.getDimension(),
                bookDetailsRequest.getCoverType(),
                bookDetailsRequest.getNumberOfPages(),
                bookDetailsRequest.getPublishingHouse(),
                bookDetailsRequest.getDescription()));
        book.setBookDetails(bookDetails);
        // set authors
        listAuthor.stream().forEach(author -> {
            Author author1 = authorService.saveAuthor(new Author(author));
            book.addAuthor(author1);
        });
        // set categories
        listCategory.stream().forEach(category -> {
            Category category1 = categoryService.retrieveByCategoryName(category);
            book.addCategory(category1);
        });
        // set images
        listImage.stream().forEach(image -> {
            String fileName = "image_" + System.currentTimeMillis() + image.getOriginalFilename();
            String imagePath = "D:/Projects/BookStoreImages/" + fileName;
            try {
                image.transferTo(new File(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BookImage bookImage = bookImageService.saveBookImage(BookImage.builder().imagePath(imagePath).build());
            book.addBookImage(bookImage);
        });
        // update book;
        bookService.updateBook(book);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> UpdateBook(@RequestParam Integer bookId, @Valid @RequestBody BookRequest bookRequest){
        Book book = bookService.retrieveById(bookId);
        book.setTitle(bookRequest.getTitle());
        book.setPrice(bookRequest.getPrice());
        book.setCurrentQuantity(bookRequest.getCurrentQuantity());
        bookService.updateBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> DeleteOneBookForShop(@RequestParam Integer bookId){
        Book book = bookService.retrieveById(bookId);
        bookService.deleteBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <----------------- Uri.BOOKS_DETAILS -----------------> */
    @GetMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> RetrieveBookDetailForBook(@RequestParam Integer bookId){
        BookDetailsDto bookDetailsDto = bookModuleService.convertToBookDetailsDto(bookService.retrieveBookDetailsByBookId(bookId));
        return new ResponseEntity<>(bookDetailsDto, HttpStatus.OK);
    }

    @PutMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> UpdateBookDetailForBook(@RequestParam Integer bookId, @Valid @RequestBody BookDetailsRequest bookDetailsRequest){
        BookDetails bookDetails = bookService.retrieveBookDetailsByBookId(bookId);
        bookDetails.setPublisher(bookDetailsRequest.getPublisher());
        bookDetails.setPublicationDate(bookDetailsRequest.getPublicationDate());
        bookDetails.setDimension(bookDetailsRequest.getDimension());
        bookDetails.setCoverType(bookDetailsRequest.getCoverType());
        bookDetails.setNumberOfPages(bookDetailsRequest.getNumberOfPages());
        bookDetails.setPublishingHouse(bookDetailsRequest.getPublishingHouse());
        bookDetails.setDescription(bookDetailsRequest.getDescription());
        bookDetailsService.updateBookDetails(bookDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> DeleteBookDetailForBook(@RequestParam Integer bookId){
        BookDetails bookDetails = bookService.retrieveBookDetailsByBookId(bookId);
        bookDetailsService.deleteBookDetails(bookDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <------------------- Uri.BOOKS_IMAGES ---------------------> */

    @GetMapping(value = {Uri.BOOKS_IMAGES})
    public ResponseEntity<?> RetrieveBookImageForBook(@RequestParam Integer bookId) {
        List<BookImageDto> bookImageDtoList = bookImageMapper.bookImageToDto(bookService.retrieveBookImagesByBookId(bookId));
        return ResponseEntity.ok(bookImageDtoList);
    }

    @PutMapping(value = {Uri.BOOKS_IMAGES} , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> UpdateBookImageForBook(@RequestParam Integer bookId, @RequestBody List<MultipartFile> images) {
        Book book = bookService.retrieveById(bookId);
        // delete BookImages
        bookService.retrieveBookImagesByBookId(bookId).stream().forEach(bookImage -> {
            bookImageService.deleteBookImage(bookImage);
        });
        // Set new BookImages
        images.stream().forEach(image -> {
            String fileName = "image_" + System.currentTimeMillis() + image.getOriginalFilename();
            String imagePath = "D:/Projects/BookStoreImages/" + fileName;
            try {
                image.transferTo(new File(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BookImage bookImage = BookImage.builder().imagePath(imagePath).build();
            book.addBookImage(bookImage);
        });
        // update
        bookService.updateBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = {Uri.BOOKS_IMAGES})
    public ResponseEntity<?> DeleteBookImageForBook(@RequestParam Integer bookId){
        bookService.retrieveBookImagesByBookId(bookId).stream().forEach(bookImage -> {
            bookImageService.deleteBookImage(bookImage);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <------------------- Uri.BOOKS_AUTHORS ---------------------> */

    @GetMapping(value = {Uri.BOOKS_AUTHORS})
    public ResponseEntity<?> RetrieveBookAuthorForBook(@RequestParam Integer bookId){
        List<AuthorDto> authorDtos = bookModuleService.convertToListAuthorDto(bookService.retrieveAuthorsByBookId(bookId));
        return new ResponseEntity<>(authorDtos, HttpStatus.OK);
    }

    @PutMapping(value = {Uri.BOOKS_AUTHORS})
    public ResponseEntity<?> UpdateBookAuthorForBook(@RequestParam Integer bookId, @RequestBody List<String> authors){
        // delete old authors
        List<Author> authorList = bookService.retrieveAuthorsByBookId(bookId);
        authorList.stream().forEach(author -> authorService.deleteAuthor(author));

        // get book;
        Book book = bookService.retrieveById(bookId);
        authors.stream().forEach(author -> {
            Author author1 = authorService.saveAuthor(new Author(author));
            book.addAuthor(author1);
        });
        // update
        bookService.updateBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.BOOKS_AUTHORS})
    public ResponseEntity<?> DeleteBookAuthorForBook(@RequestParam Integer bookId){
        // delete old authors
        List<Author> authorList = bookService.retrieveAuthorsByBookId(bookId);
        authorList.stream().forEach(author -> authorService.deleteAuthor(author));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /* <----------------------- Uri.BOOKS_CATEGORIES -------------------> */

    @GetMapping(value = {Uri.BOOKS_CATEGORIES})
    public ResponseEntity<?> RetrieveBookCategoryForBook(@RequestParam Integer bookId){
        List<CategoryDto> categoryDtos = categoryModuleService.convertToListCategoryDto(categoryService.retrieveCategoriesByBooksId(bookId));
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @PutMapping(value = {Uri.BOOKS_CATEGORIES})
    public ResponseEntity<?> UpdateBookCategoryForBook(@RequestParam Integer bookId, @RequestBody List<Integer> categoriesId){
        Book book = bookService.retrieveById(bookId);
        Set<Category> categories = new HashSet<>();
        categoriesId.stream().forEach(id -> {
            categories.add(categoryService.retrieveById(id));
        });
        categoryService.deleteBookCategoriesByBookId(bookId);
        book.setCategories(categories);
        bookService.updateBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.BOOKS_CATEGORIES})
    public ResponseEntity<?> DeleteBookCategoryForBook(@RequestParam Integer bookId){
        categoryService.deleteBookCategoriesByBookId(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <----------------------- Uri.BOOKS_REVIEWS -------------------> */

    // updating
    @GetMapping(value = {Uri.BOOKS_REVIEWS})
    public ResponseEntity<?> RetrieveReviewsForBook(@RequestParam Integer bookId, @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        List<ReviewDto>reviewDtos = bookModuleService.convertToListReviewDto(reviewService.retrieveReviewsByPage(bookId, pageable));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <----------------------- Uri.BOOKS_RATES ---------------------> */

    @GetMapping(value = {Uri.BOOKS_RATES})
    public ResponseEntity<?> RetrieveAllRateForBook(@RequestParam Integer bookId){
        RateDto rateDto = bookModuleService.convertToRateDto(bookId);
        return new ResponseEntity<>(rateDto, HttpStatus.OK);
    }

}

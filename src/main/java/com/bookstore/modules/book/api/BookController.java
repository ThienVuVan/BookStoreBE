package com.bookstore.modules.book.api;

import com.bookstore.common.entity.*;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.*;
import com.bookstore.modules.book.dto.*;
import com.bookstore.modules.book.request.*;
import com.bookstore.modules.book.response.BookDetailResponse;
import com.bookstore.modules.book.service.BookModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookDetailsService bookDetailsService;
    private final AuthorService authorService;
    private final ReviewService reviewService;
    private final CategoryService categoryService;
    private final BookImageService bookImageService;
    private final BookModuleService bookModuleService;
    private final ShopService shopService;

    @GetMapping(value = {Uri.BOOKS_PAGE})
    public ResponseEntity<?> RetrieveBooksByPage(@RequestParam Integer page){
        Pageable pageRequest = PageRequest.of(page, 8);
        List<BookDto> bookDtos = bookModuleService.convertPageToListBookDto(
                bookService.retrieveBooksByPage(pageRequest));
        bookDtos.stream().forEach(bookDto -> {
            String imagePath = bookService.retrieveBookImagesByBookId(bookDto.getId()).get(0).getImagePath();
            bookDto.setImagePath(imagePath);
        });
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @GetMapping(value = Uri.BOOKS)
    public ResponseEntity<?> RetrieveBookById(@RequestParam Integer bookId){
        BookDto bookDto = bookModuleService.convertToBookDto(bookService.retrieveById(bookId));
        String imagePath = bookService.retrieveBookImagesByBookId(bookDto.getId()).get(0).getImagePath();
        bookDto.setImagePath(imagePath);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> RetrieveBookDetailById(@RequestParam Integer bookId){
        Book book = bookService.retrieveById(bookId);
        List<String> authors = book.getAuthors()
                .stream().map(author -> new String(author.getName())).collect(Collectors.toList());
        List<String> categories = book.getCategories()
                .stream().map(category -> new String(category.getName())).collect(Collectors.toList());
        BookDetails bookDetails = bookService.retrieveBookDetailsByBookId(bookId);
        List<String> images = bookService.retrieveBookImagesByBookId(bookId)
                .stream().map(image -> new String(image.getImagePath())).collect(Collectors.toList());
        Integer shopId = shopService.retrieveShopByBookId(bookId).getId();
        BookDetailResponse bookResponse = BookDetailResponse.builder()
                .id(book.getId())
                .shopId(shopId)
                .title(book.getTitle())
                .price(book.getPrice())
                .currentQuantity(book.getCurrentQuantity())
                .soldQuantity(book.getSoldQuantity())
                .publisher(bookDetails.getPublisher())
                .publicationDate(bookDetails.getPublicationDate())
                .dimension(bookDetails.getDimension())
                .coverType(bookDetails.getCoverType())
                .numberOfPages(bookDetails.getNumberOfPages())
                .publishingHouse(bookDetails.getPublishingHouse())
                .description(bookDetails.getDescription())
                .author(authors.get(0))
                .category(categories.get(0))
                .images(images)
                .build();
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping(value = {Uri.BOOKS_SHOP})
    public ResponseEntity<?> RetrieveBookByShopId(@RequestParam Integer shopId){
        List<BookDto> bookDtos = bookModuleService.convertToListBookDto(bookService.retrieveBookByShopId(shopId));
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
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

    @PostMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> CreateNewBook(@RequestParam Integer shopId, @Valid @ModelAttribute BookRequest bookRequest){
        // find shop
        Shop shop = shopService.retrieveShopById(shopId);
        // find category
        Category category = categoryService.retrieveById(bookRequest.getCategoryId());
        // save book;
        Book book = bookService.saveBook(new Book(bookRequest.getTitle(), bookRequest.getPrice(), bookRequest.getCurrentQuantity()));
        // set category
        book.addCategory(category);
        // save bookDetails;
        BookDetails bookDetails = bookDetailsService.saveBookDetails(new BookDetails(
                bookRequest.getPublisher(),
                bookRequest.getPublicationDate(),
                bookRequest.getDimension(),
                bookRequest.getCoverType(),
                bookRequest.getNumberOfPages(),
                bookRequest.getPublishingHouse(),
                bookRequest.getDescription()));
        book.setBookDetails(bookDetails);
        // set images
        bookRequest.getImages().stream().forEach(image -> {
            String fileName = "image_" + System.currentTimeMillis() + image.getOriginalFilename();
            String imagePath = "D:/Projects/BookStoreFE/public/images/" + fileName;
            try {
                image.transferTo(new File(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BookImage bookImage = bookImageService.saveBookImage(BookImage.builder().imagePath(imagePath).build());
            book.addBookImage(bookImage);
        });
        // set authors
        Author author = authorService.saveAuthor(new Author(bookRequest.getAuthor()));
        book.addAuthor(author);
        // update book;
        bookService.updateBook(book);
        // update shop
        shop.addBook(book);
        shopService.updateShop(shop);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> UpdateBook(@RequestParam Integer bookId, @Valid @ModelAttribute BookUpdateRequest bookUpdateRequest){
        Book book = bookService.retrieveById(bookId);
        book.setTitle(bookUpdateRequest.getTitle());
        book.setPrice(bookUpdateRequest.getPrice());
        book.setCurrentQuantity(bookUpdateRequest.getCurrentQuantity());

        if(bookUpdateRequest.getNewImages().size() != 0){
            List<BookImage> bookImages = bookService.retrieveBookImagesByBookId(bookId);
            bookImages.stream().forEach(bookImage -> bookImageService.deleteBookImage(bookImage));
            bookUpdateRequest.getNewImages().stream().forEach(image -> {
                String fileName = "image_" + System.currentTimeMillis() + image.getOriginalFilename();
                String imagePath = "D:/Projects/BookStoreFE/public/images/" + fileName;
                try {
                    image.transferTo(new File(imagePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                BookImage bookImage = bookImageService.saveBookImage(BookImage.builder().imagePath(imagePath).build());
                book.addBookImage(bookImage);
            });
        }
        if(bookUpdateRequest.getNewCategoryId() != null){
            bookService.deleteBookCategory(bookId);
            book.setCategories(null);
            Category category = categoryService.retrieveById(bookUpdateRequest.getNewCategoryId());
            book.addCategory(category);
        }

        BookDetails bookDetails = bookService.retrieveBookDetailsByBookId(bookId);
        bookDetails.setPublisher(bookUpdateRequest.getPublisher());
        bookDetails.setPublicationDate(bookUpdateRequest.getPublicationDate());
        bookDetails.setDimension(bookUpdateRequest.getDimension());
        bookDetails.setCoverType(bookUpdateRequest.getCoverType());
        bookDetails.setNumberOfPages(bookUpdateRequest.getNumberOfPages());
        bookDetails.setPublishingHouse(bookUpdateRequest.getPublishingHouse());
        bookDetails.setDescription(bookUpdateRequest.getDescription());

        bookService.updateBook(book);
        bookDetailsService.updateBookDetails(bookDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> DeleteOneBookForShop(@RequestParam Integer bookId){
        Book book = bookService.retrieveById(bookId);
        bookService.deleteBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /* <----------------------- Uri.BOOKS_REVIEWS -------------------> */

    @GetMapping(value = {Uri.BOOKS_REVIEWS})
    public ResponseEntity<?> RetrieveReviewsForBook(@RequestParam Integer bookId, @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        List<ReviewDto> reviewDtos = bookModuleService.convertToListReviewDto(reviewService.retrieveReviewsByPage(bookId, pageable));
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    /* <----------------------- Uri.BOOKS_RATES ---------------------> */

    @GetMapping(value = {Uri.BOOKS_RATES})
    public ResponseEntity<?> RetrieveAllRateForBook(@RequestParam Integer bookId){
        RateDto rateDto = bookModuleService.convertToRateDto(bookId);
        return new ResponseEntity<>(rateDto, HttpStatus.OK);
    }
}

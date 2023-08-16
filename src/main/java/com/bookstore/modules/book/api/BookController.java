package com.bookstore.modules.book.api;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.BookImage;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.BookImageService;
import com.bookstore.common.service.BookService;
import com.bookstore.modules.book.dto.BookImageDto;
import com.bookstore.modules.book.mapper.BookImageMapper;
import com.bookstore.modules.book.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final BookImageService bookImageService;
    private final BookImageMapper bookImageMapper;

    @GetMapping(value = {Uri.BOOKS_PAGE})
    public ResponseEntity<?> RetrieveBooksByPage(@RequestParam Integer page){
        return null;
    }

    @GetMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> RetrieveBookById(@RequestParam Integer bookId){
        return null;
    }

    @GetMapping(value = {Uri.BOOKS_FILTER})
    public ResponseEntity<?> RetrieveBookByCondition(@Valid @RequestBody BookSearchRequest bookSearchRequest){
        return null;
    }

    @PostMapping(value = {Uri.BOOKS}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> CreateNewBook(@RequestParam Integer shopId,
                                           @Valid @RequestBody BookRequest bookRequest,
                                           @Valid @RequestBody BookDetailsRequest bookDetailsRequest,
                                           @RequestBody List<String> listAuthor,
                                           @RequestBody List<String> listCategory,
                                           @RequestBody List<MultipartFile> listImage){
        // update later
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> UpdateBook(@RequestParam Integer bookId, @Valid @RequestBody BookRequest bookRequest){
        return null;
    }

    @DeleteMapping(value = {Uri.BOOKS})
    public ResponseEntity<?> DeleteOneBookForShop(@RequestParam Integer bookId){
        return null;
    }

    /* <----------------- Uri.BOOKS_DETAILS -----------------> */
    @GetMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> RetrieveBookDetailForBook(@RequestParam Integer bookId){
        return null;
    }

    @PutMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> UpdateBookDetailForBook(@RequestParam Integer bookId, @Valid @RequestBody BookDetailsRequest bookDetailsRequest){
        return null;
    }

    @DeleteMapping(value = {Uri.BOOKS_DETAILS})
    public ResponseEntity<?> DeleteBookDetailForBook(@RequestParam Integer bookId){
        return null;
    }

    /* <------------------- Uri.BOOKS_IMAGES ---------------------> */

    // ok
//    @GetMapping(value = {Uri.BOOKS_IMAGES})
//    public ResponseEntity<?> RetrieveBookImageForBook(@RequestParam Integer bookId) {
//        List<BookImageDto> bookImageDtoList = bookImageMapper.bookImageToDto(bookService.retrieveBookImagesByBookId(bookId));
//        return ResponseEntity.ok(bookImageDtoList);
//    }

    @PutMapping(value = {Uri.BOOKS_IMAGES} , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> UpdateBookImageForBook(@RequestParam Integer bookId, @RequestBody List<MultipartFile> images) {
        Book book = bookService.retrieveById(bookId);
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
        bookService.updateBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = {Uri.BOOKS_IMAGES})
    public ResponseEntity<?> DeleteBookImageForBook(@RequestParam Integer bookId){
        return null;
    }

    /* <------------------- Uri.BOOKS_AUTHORS ---------------------> */

    @GetMapping(value = {Uri.BOOKS_AUTHORS})
    public ResponseEntity<?> RetrieveBookAuthorForBook(@RequestParam Integer bookId){
        return null;
    }

    @PutMapping(value = {Uri.BOOKS_AUTHORS})
    public ResponseEntity<?> UpdateBookAuthorForBook(@RequestParam Integer bookId, @RequestBody List<String> authors){
        return null;
    }

    @DeleteMapping(value = {Uri.BOOKS_AUTHORS})
    public ResponseEntity<?> DeleteBookAuthorForBook(@RequestParam Integer bookId){
        return null;
    }


    /* <----------------------- Uri.BOOKS_CATEGORIES -------------------> */

    @GetMapping(value = {Uri.BOOKS_CATEGORIES})
    public ResponseEntity<?> RetrieveBookCategoryForBook(@RequestParam Integer bookId){
        return null;
    }

    @PutMapping(value = {Uri.BOOKS_CATEGORIES})
    public ResponseEntity<?> UpdateBookCategoryForBook(@RequestParam Integer bookId){
        return null;
    }

    @DeleteMapping(value = {Uri.BOOKS_CATEGORIES})
    public ResponseEntity<?> DeleteBookCategoryForBook(@RequestParam Integer bookId){
        return null;
    }

    /* <----------------------- Uri.BOOKS_REVIEWS -------------------> */

    @GetMapping(value = {Uri.BOOKS_REVIEWS})
    public ResponseEntity<?> RetrieveAllReviewsForBook(@RequestParam Integer bookId, @RequestParam Integer page){
        return null;
    }

    /* <----------------------- Uri.BOOKS_RATES ---------------------> */

    @GetMapping(value = {Uri.BOOKS_RATES})
    public ResponseEntity<?> RetrieveAllRateForBook(@RequestParam Integer bookId){
        return null;
    }

}

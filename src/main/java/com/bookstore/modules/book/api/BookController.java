package com.bookstore.modules.book.api;

import com.bookstore.common.entity.Book;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Retrieve All Books", description = "Retrieve All Books")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = {URI.BOOKS})
    public ResponseEntity<?> RetrieveAllBook(){
        return new ResponseEntity<>(bookService.retrieveAllBooks(), HttpStatus.OK);
    }

    @Operation(summary = "Post a new book", description = "Post a new book")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = {URI.BOOKS})
    public ResponseEntity<?> CreateNewBook(@Valid @RequestBody Book book){
        bookService.saveBook(book);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.BOOKS})
    public ResponseEntity<?> UpdateUser(@Valid @RequestBody Book book){
        bookService.updateBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.BOOKS})
    public ResponseEntity<?> DeleteUser(@Valid @RequestBody Book book){
        bookService.deleteBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }
}

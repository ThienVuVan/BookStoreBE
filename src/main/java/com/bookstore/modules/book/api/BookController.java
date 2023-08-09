package com.bookstore.modules.book.api;

import com.bookstore.common.entity.Book;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.BookService;
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
    @GetMapping(value = {URI.BOOKS})
    public ResponseEntity<List<?>> RetrieveAllBook(){
        return new ResponseEntity<>(bookService.retrieveAllBooks(), HttpStatus.OK);
    }
    @PostMapping(value = {URI.BOOKS})
    public ResponseEntity CreateNewBook(@Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        bookService.saveBook(book);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.BOOKS})
    public ResponseEntity UpdateUser(@Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        bookService.updateBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.BOOKS})
    public ResponseEntity DeleteUser(@Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        bookService.deleteBook(book);
        return new ResponseEntity(HttpStatus.OK);
    }
}

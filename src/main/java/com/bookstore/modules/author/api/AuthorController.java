package com.bookstore.modules.author.api;

import com.bookstore.common.entity.Author;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping(value = {URI.AUTHORS})
    public ResponseEntity<List<?>> RetrieveAllAuthor(){
        return new ResponseEntity<>(authorService.retrieveAllAuthor(), HttpStatus.OK);
    }
    @PostMapping(value = {URI.AUTHORS})
    public ResponseEntity CreateNewAuthor(@Valid @RequestBody Author author, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        authorService.saveAuthor(author);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.AUTHORS})
    public ResponseEntity UpdateAuthor(@Valid @RequestBody Author author, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        authorService.updateAuthor(author);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.AUTHORS})
    public ResponseEntity DeleteAuthor(@Valid @RequestBody Author author, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        authorService.deleteAuthor(author);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package com.bookstore.modules.category.api;

import com.bookstore.common.entity.Category;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping(value = {URI.CATEGORIES})
    public ResponseEntity<List<?>> RetrieveAllCategory(){
        return new ResponseEntity<>(categoryService.retrieveAllCategory(), HttpStatus.OK);
    }
    @PostMapping(value = {URI.CATEGORIES})
    public ResponseEntity CreateNewCategory(@Valid @RequestBody Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        categoryService.saveCategory(category);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.CATEGORIES})
    public ResponseEntity UpdateCategory(@Valid @RequestBody Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        categoryService.updateCategory(category);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.CATEGORIES})
    public ResponseEntity DeleteCategory(@Valid @RequestBody Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        categoryService.deleteCategory(category);
        return new ResponseEntity(HttpStatus.OK);
    }
}

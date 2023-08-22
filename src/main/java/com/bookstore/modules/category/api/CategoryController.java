package com.bookstore.modules.category.api;

import com.bookstore.common.entity.Category;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.CategoryService;
import com.bookstore.modules.category.dto.CategoryDto;
import com.bookstore.modules.category.request.CategoryRequest;
import com.bookstore.modules.category.service.CategoryModuleService;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryModuleService categoryModuleService;

    @GetMapping(value = {Uri.PARENT_CATEGORIES})
    public ResponseEntity<?> RetrieveAllParentCategory() {
        return ResponseEntity.ok(categoryModuleService.convertToListCategoryDto(categoryService.retrieveAllParentCategory()));
    }

    @GetMapping(value = {Uri.CHILD_CATEGORIES})
    public ResponseEntity<?> RetrieveAllChildCategoryByParentId(@RequestParam Integer parentId){
        List<CategoryDto> categoryDtos = categoryModuleService.convertToListCategoryDto(
                categoryService.retrieveCategoriesByParentId(parentId));
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @PostMapping(value = {Uri.CATEGORIES})
    public ResponseEntity<?> CreateCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category = new Category(categoryRequest.getName(), categoryRequest.getParenId());
        categoryService.saveCategory(category);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = {Uri.CATEGORIES})
    public ResponseEntity<?> UpdateCategory(@RequestParam Integer id, @RequestParam String newName){
        Category category = categoryService.retrieveById(id);
        category.setName(newName);
        categoryService.updateCategory(category);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.PARENT_CATEGORIES})
    public ResponseEntity<?> DeleteParentCategory(@RequestParam Integer id){
        Category category = categoryService.retrieveById(id);
        categoryService.retrieveCategoriesByParentId(id).stream().forEach(category1 -> categoryService.deleteCategory(category1));
        categoryService.deleteCategory(category);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.CHILD_CATEGORIES})
    public ResponseEntity<?> DeleteChildCategory(@RequestParam Integer id){
        categoryService.deleteCategory(categoryService.retrieveById(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}

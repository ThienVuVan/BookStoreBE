package com.bookstore.modules.category.api;

import com.bookstore.common.entity.Category;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.CategoryService;
import com.bookstore.modules.category.dto.CategoryDto;
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
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = {Uri.PARENT_CATEGORIES})
    public ResponseEntity<?> RetrieveAllParentCategory() {
        return ResponseEntity.ok(categoryModuleService.CategoryToCategoryDto(categoryService.retrieveAllParentCategory()));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = {Uri.CHILD_CATEGORIES})
    public ResponseEntity<?> RetrieveAllChildCategoryByParentName(@RequestParam String parentName){
        Category parentCategory = categoryService.retrieveByCategoryName(parentName);
        List<CategoryDto> childCategories = categoryModuleService.CategoryToCategoryDto(categoryService.retrieveByParentId(parentCategory.getId()));
        return ResponseEntity.ok(childCategories);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = {Uri.PARENT_CATEGORIES})
    public ResponseEntity<?> CreateParentCategory(@RequestParam String newName){
        Category category = new Category(newName, null);
        categoryService.saveCategory(category);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = {Uri.CHILD_CATEGORIES})
    public ResponseEntity<?> CreateChildCategory(@RequestParam String parentName, @RequestParam String childName){
        Category parentCategory = categoryService.retrieveByCategoryName(parentName);
        Category childCategory = new Category(childName, parentCategory.getId());
        categoryService.saveCategory(childCategory);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = {Uri.PARENT_CATEGORIES})
    public ResponseEntity<?> UpdateParentCategory(@RequestParam String oldName, @RequestParam String newName){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = {Uri.CHILD_CATEGORIES})
    public ResponseEntity<?> UpdateChildCategory(@RequestParam String oldName, @RequestParam String newName){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.PARENT_CATEGORIES})
    public ResponseEntity<?> DeleteParentCategory(@RequestParam String parentName){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.CHILD_CATEGORIES})
    public ResponseEntity<?> DeleteChildCategory(@RequestParam String childName){
        return new ResponseEntity(HttpStatus.OK);
    }
}

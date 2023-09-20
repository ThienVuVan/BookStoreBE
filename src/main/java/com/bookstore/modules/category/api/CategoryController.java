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

    @GetMapping(value = Uri.CATEGORIES)
    public ResponseEntity<?> RetrieveAllCategories(){
        List<CategoryDto> categoryDtoList = categoryModuleService.convertToListCategoryDto(categoryService.retrieveAllParentCategory());
        categoryDtoList.stream().forEach((category) -> {
            category.setSubcategories(categoryModuleService.convertToListCategoryDto(categoryService.retrieveCategoriesByParentId(category.getId())));
        });
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }
}

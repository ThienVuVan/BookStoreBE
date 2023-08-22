package com.bookstore.modules.category.service;

import com.bookstore.common.entity.Category;
import com.bookstore.modules.category.dto.CategoryDto;
import com.bookstore.modules.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryModuleService {
    private final CategoryMapper categoryMapper;
    public List<CategoryDto> convertToListCategoryDto(List<Category> categories){
        return categories.stream().map(category -> categoryMapper.CategoryToCategoryDto(category)).collect(Collectors.toList());
    }
}

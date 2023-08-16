package com.bookstore.modules.category.mapper;

import com.bookstore.common.entity.Category;
import com.bookstore.modules.category.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto CategoryToCategoryDto(Category category);
}

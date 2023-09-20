package com.bookstore.modules.category.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {
    Integer id;
    String name;
    Integer parentId;
    Boolean isOpen = false;
    List<CategoryDto> subcategories;
}

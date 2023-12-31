package com.bookstore.modules.book.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    Integer id;
    String title;
    Double price;
    String author;
    Integer currentQuantity;
    Integer soldQuantity;
    String imagePath;
}

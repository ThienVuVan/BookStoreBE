package com.bookstore.modules.book.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDetailResponse {
    Integer id;
    Integer shopId;
    String shopName;
    String title;
    Double price;
    Integer currentQuantity;
    Integer soldQuantity;
    String publisher;
    LocalDate publicationDate;
    String dimension;
    String coverType;
    Integer numberOfPages;
    String publishingHouse;
    String description;
    String author;
    String category;
    List<String> images;
}

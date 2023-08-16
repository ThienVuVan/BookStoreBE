package com.bookstore.modules.book.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDetailsDto {
    Integer id;
    String publisher;
    String publicationDate;
    String Dimension;
    String coverType;
    String numberOfPages;
    String publishingHouse;
    String description;
}

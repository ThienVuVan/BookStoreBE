package com.bookstore.modules.book.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    Integer id;
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

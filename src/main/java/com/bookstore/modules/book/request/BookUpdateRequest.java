package com.bookstore.modules.book.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookUpdateRequest {
    @NotBlank
    @Length(min = 1)
    String title;

    @NotNull
    @PositiveOrZero
    Double price;

    @NotNull
    @Range(max = 10000, min = 0)
    Integer currentQuantity;

    String publisher;
    @Temporal(TemporalType.DATE)
    LocalDate publicationDate;
    String dimension;
    String coverType;
    Integer numberOfPages;
    String publishingHouse;
    String description;
    String authors;
    Integer newCategoryId;
    List<MultipartFile> newImages;
}

package com.bookstore.modules.book.request;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDetailsRequest {
    String publisher;

    @Temporal(TemporalType.DATE)
    LocalDate publicationDate;

    String dimension;

    String coverType;

    Integer numberOfPages;

    String publishingHouse;

    String description;
}

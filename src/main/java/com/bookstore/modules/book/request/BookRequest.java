package com.bookstore.modules.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {

    @NotBlank
    @Length(min = 1)
    String title;

    @NotNull
    @PositiveOrZero
    Double price;

    @NotNull
    @Range(max = 10000, min = 0)
    Integer currentQuantity;
}

package com.bookstore.modules.shop.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookSearchRequest {
    @Length(min = 1)
    String title;
    @PositiveOrZero
    Double price;
    @Range(max = 10000, min = 0)
    Integer currentQuantity;
    @Range(max = 10000, min = 0)
    Integer soldQuantity;
}

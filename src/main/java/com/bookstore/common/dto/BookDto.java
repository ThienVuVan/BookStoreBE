package com.bookstore.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String title;
    private Double price;
    private Integer currentQuantity;
    private Integer soldQuantity;
}

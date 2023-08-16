package com.bookstore.modules.book.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookUpdateRequest {
    String title;
    Double price;
    Integer currentQuantity;
}

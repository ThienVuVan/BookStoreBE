package com.bookstore.modules.order.dto;

import com.bookstore.modules.book.dto.BookDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDto {
    Integer id;
    BookDto bookDto;
    Integer quantity;
}

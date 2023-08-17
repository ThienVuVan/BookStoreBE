package com.bookstore.modules.order.response;

import com.bookstore.modules.book.dto.BookDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDto {
    Integer id;
    BookDto bookDto;
    Integer quantity;
}

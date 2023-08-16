package com.bookstore.modules.order.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Integer id;
    LocalDate date;
    Double totalPrice;
    String DeliveryAddress;
    Boolean orderStatus;
}

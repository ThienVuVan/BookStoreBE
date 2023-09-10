package com.bookstore.modules.shop.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderSearchRequest {
    Integer id;
    LocalDate date;
    Double totalPrice;
    String DeliveryAddress;
    String orderStatus;
}

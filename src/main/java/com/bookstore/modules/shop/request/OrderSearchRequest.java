package com.bookstore.modules.shop.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
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

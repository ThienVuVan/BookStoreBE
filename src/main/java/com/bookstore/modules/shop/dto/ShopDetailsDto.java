package com.bookstore.modules.shop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopDetailsDto {
    Integer id;
    String description;
    String operationHours;
    String shippingPolicy;
    String returnPolicy;
}

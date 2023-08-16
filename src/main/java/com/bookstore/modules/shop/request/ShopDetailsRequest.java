package com.bookstore.modules.shop.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopDetailsRequest {
    String description;
    String operationHours;
    String shippingPolicy;
    String returnPolicy;
}

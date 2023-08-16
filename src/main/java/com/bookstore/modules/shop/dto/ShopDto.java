package com.bookstore.modules.shop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopDto {
    Integer id;
    String shopLogoPath;
    String shopName;
    String shopAddress;
    String contactPhone;
    String contactEmail;
}

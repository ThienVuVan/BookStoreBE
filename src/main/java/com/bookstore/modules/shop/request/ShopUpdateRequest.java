package com.bookstore.modules.shop.request;

import com.bookstore.common.annotation.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopUpdateRequest {
    @NotNull
    Integer userId;

    @NotBlank
    @Length(min = 2)
    String shopName;

    String shopAddress;
    @Phone
    String contactPhone;
    @Email
    String contactEmail;
}

package com.bookstore.modules.shop.request;

import com.bookstore.common.annotation.Phone;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopRequest implements Serializable {
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
    String description;
    String operationHours;
    String shippingPolicy;
    String returnPolicy;
    @NotNull
    MultipartFile shopLogo;
}

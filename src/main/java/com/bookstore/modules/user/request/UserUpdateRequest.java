package com.bookstore.modules.user.request;

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
public class UserUpdateRequest {
    @NotNull
    Integer id;

    @NotBlank
    @Length(max = 50, min = 5)
    String username;

    @NotBlank
    @Phone(message = "must be a well-formed phone number")
    String phoneNumber;

    @NotBlank
    @Email
    String email;
}

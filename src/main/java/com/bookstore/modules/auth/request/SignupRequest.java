package com.bookstore.modules.auth.request;

import com.bookstore.common.annotation.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequest {
    @NotBlank
    @Length(max = 50, min = 5)
    String username;

    @NotBlank
    @Phone(message = "must be a well-formed phone number")
    String phoneNumber;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Length(max = 1000, min = 8)
    String password;

    List<String> roles;


}

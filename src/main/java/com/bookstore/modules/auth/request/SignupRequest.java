package com.bookstore.modules.auth.request;

import com.bookstore.common.annotation.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Length(max = 50, min = 5)
    private String username;

    @NotBlank
    @Phone(message = "must be a well-formed phone number")
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(max = 1000, min = 8)
    private String password;

    @NotEmpty
    Set<String> roles;
}

package com.bookstore.modules.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @Length(max = 50, min = 5)
    private String username;

    @NotBlank
    @Length(max = 1000, min = 8)
    private String password;
}

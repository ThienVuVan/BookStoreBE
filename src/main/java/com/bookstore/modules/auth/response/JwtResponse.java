package com.bookstore.modules.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Integer id;
    private String username;
    private List<String> roles;
    private String token;
    private String refreshToken;
}

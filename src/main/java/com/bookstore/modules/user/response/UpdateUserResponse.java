package com.bookstore.modules.user.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserResponse {
    Integer id;
    String username;
    String phoneNumber;
    String email;
    String token;
    String refreshToken;
}

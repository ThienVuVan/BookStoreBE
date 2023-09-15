package com.bookstore.modules.auth.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialRequest {
    @NotNull
    String username;
    @NotNull
    String email;
    @NotNull
    String socialId;
    @NotNull
    Integer type;
}

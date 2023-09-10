package com.bookstore.modules.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewRateCreateRequest {
    @NotNull
    Integer userId;
    @NotNull
    Integer bookId;
    @NotBlank
    @Length(min = 1)
    String comment;
    Integer rate;
    MultipartFile image;
}

package com.bookstore.modules.user.request;

import com.bookstore.common.entity.User;
import jakarta.persistence.*;
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
public class ReviewCreateRequest {
    @NotNull
    Integer bookId;

    @NotBlank
    @Length(min = 1)
    String comment;
}

package com.bookstore.modules.user.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RateCreateRequest {
    @NotNull
    Integer userId;

    @NotNull
    Integer bookId;

    @NotNull
    @PositiveOrZero
    @Range(max = 5, min = 0)
    Integer rating;
}

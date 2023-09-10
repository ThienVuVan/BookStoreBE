package com.bookstore.modules.book.request;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookSearchRequest {
    String title;
    String authors;
    Double lowPrice;
    Double highPrice;
    String category;
}

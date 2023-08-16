package com.bookstore.modules.order.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {
    @NotNull
    Integer bookId;

    @NotNull
    @Positive
    Integer quantity;
}

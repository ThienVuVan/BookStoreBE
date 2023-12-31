package com.bookstore.modules.order.request;

import com.bookstore.common.entity.OrderItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    @NotNull
    Integer userId;

    @NotNull
    Integer shopId;

    @NotNull
    @PositiveOrZero
    Double totalPrice;

    String address;

    Boolean orderStatus;

    List<OrderItemRequest> orderItems;
}

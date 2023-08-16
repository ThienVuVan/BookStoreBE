package com.bookstore.modules.order.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;


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
    @Column(name = "total_price")
    Double totalPrice;

    @NotBlank
    @Length(min = 5)
    @Column(name = "delivery_address")
    String DeliveryAddress;

    @NotNull
    @Column(name = "order_status")
    Boolean orderStatus;
}

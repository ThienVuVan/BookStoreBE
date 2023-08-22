package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @PastOrPresent
    @Column(name = "date")
    LocalDate date;

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
    String orderStatus;

    /* <---------- Entity Method -----------> */

    public Order(LocalDate date, Double totalPrice, String deliveryAddress, String orderStatus) {
        this.date = date;
        this.totalPrice = totalPrice;
        DeliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
    }

    /* <---------------------- Convenience Method ---------------------> */
    public void addOrderItem(OrderItem orderItem){
        if(orderItems == null) orderItems = new HashSet<>();
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", DeliveryAddress='" + DeliveryAddress + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }

    /* <---------- Mapping -----------> */

    /* To User */
    /* Delete Order, Does Not Delete User */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    User user;

    /* To OrderItem */
    /* Delete Order, Delete OrderItem */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    Set<OrderItem> orderItems;

    /* To Shop */
    /* Delete Order, Does Not Delete Shop */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    Shop shop;
}


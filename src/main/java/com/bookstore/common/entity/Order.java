package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @PastOrPresent
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @PositiveOrZero
    @Column(name = "total_price")
    private Double totalPrice;

    @NotBlank
    @Length(min = 5)
    @Column(name = "delivery_address")
    private String DeliveryAddress;

    @NotNull
    @Column(name = "order_status")
    private Boolean orderStatus;

    /* <---------- Entity Method -----------> */

    public Order(LocalDate date, Double totalPrice, String deliveryAddress, Boolean orderStatus) {
        this.date = date;
        this.totalPrice = totalPrice;
        DeliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
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
    private User user;

    /* To OrderItem */
    /* Delete Order, Delete OrderItem */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    /* To Shop */
    /* Delete Order, Does Not Delete Shop */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    private Shop shop;
}


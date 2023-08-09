package com.bookstore.common.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* Delete OrderItem, Does Not Delete Order */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private Order order;

    /* Delete OrderItem, Does Not Delete Book */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity")
    @NotNull
    @Range(max = 1000, min = 1)
    @PositiveOrZero
    private Integer quantity;

    /* <-------------- Entity Method ----------------> */

    public OrderItem(Order order, Book book, Integer quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}

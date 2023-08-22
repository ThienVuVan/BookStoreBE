package com.bookstore.common.repository;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Integer id);

    @Query("select oi from OrderItem oi where oi.order.id = :orderId")
    List<OrderItem> findOrderItemsByOrderId(Integer orderId);
}

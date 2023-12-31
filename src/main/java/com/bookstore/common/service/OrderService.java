package com.bookstore.common.service;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order saveOrder(Order order);
    public Order updateOrder(Order order);
    public void deleteOrder(Order order);
    public Order retrieveById(Integer id);
    List<OrderItem> retrieveOrderItemsByOrderId(Integer orderId);
}

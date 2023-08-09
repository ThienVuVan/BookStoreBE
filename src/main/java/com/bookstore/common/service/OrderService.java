package com.bookstore.common.service;

import com.bookstore.common.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> retrieveAllOrder();
    public Order saveOrder(Order order);
    public Order updateOrder(Order order);
    public void deleteOrder(Order order);
    public Optional<Order> retrieveById(Integer id);
}

package com.bookstore.common.service;

import com.bookstore.common.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    public List<OrderItem> retrieveAllOrderItem();
    public OrderItem saveOrderItem(OrderItem orderItem);
    public OrderItem updateOrderItem(OrderItem orderItem);
    public void deleteOrderItem(OrderItem orderItem);
}

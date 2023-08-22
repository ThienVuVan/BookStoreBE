package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.OrderItem;
import com.bookstore.common.repository.OrderRepository;
import com.bookstore.common.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<Order> retrieveAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Order retrieveById(Integer id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public List<OrderItem> retrieveOrderItemsByOrderId(Integer orderId) {
        return orderRepository.findOrderItemsByOrderId(orderId);
    }
}

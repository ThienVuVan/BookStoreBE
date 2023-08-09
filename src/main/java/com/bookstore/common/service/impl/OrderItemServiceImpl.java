package com.bookstore.common.service.impl;

import com.bookstore.common.entity.OrderItem;
import com.bookstore.common.repository.OrderItemRepository;
import com.bookstore.common.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> retrieveAllOrderItem() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }
}

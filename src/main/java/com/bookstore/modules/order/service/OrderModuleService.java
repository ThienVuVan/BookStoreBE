package com.bookstore.modules.order.service;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.OrderItem;
import com.bookstore.modules.order.dto.OrderDto;
import com.bookstore.modules.order.dto.OrderItemDto;
import com.bookstore.modules.order.mapper.OrderItemMapper;
import com.bookstore.modules.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderModuleService {
    public final OrderMapper orderMapper;
    public final OrderItemMapper orderItemMapper;
    public List<OrderDto> OrderToOrderDto(List<Order> orders){
        return orders.stream().map((order) -> orderMapper.OrderToOrderDto(order)).collect(Collectors.toList());
    }

}

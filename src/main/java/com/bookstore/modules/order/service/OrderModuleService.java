package com.bookstore.modules.order.service;

import com.bookstore.common.entity.Order;
import com.bookstore.modules.order.dto.OrderDto;
import com.bookstore.modules.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderModuleService {
    public final OrderMapper orderMapper;
    public List<OrderDto> OrderToOrderDto(List<Order> orders){
        return orderMapper.OrderToOrderDto(orders);
    }
}

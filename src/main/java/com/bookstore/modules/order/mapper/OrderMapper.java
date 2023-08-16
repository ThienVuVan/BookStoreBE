package com.bookstore.modules.order.mapper;

import com.bookstore.common.entity.Order;
import com.bookstore.modules.order.dto.OrderDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    List<OrderDto> OrderToOrderDto(List<Order> orders);
}

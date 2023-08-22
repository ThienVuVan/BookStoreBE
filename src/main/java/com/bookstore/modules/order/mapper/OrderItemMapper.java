package com.bookstore.modules.order.mapper;

import com.bookstore.common.entity.OrderItem;
import com.bookstore.modules.order.dto.OrderItemDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDto OrderItemToDto(OrderItem orderItem);
}

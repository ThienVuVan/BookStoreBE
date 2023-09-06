package com.bookstore.modules.order.api;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.OrderItem;
import com.bookstore.common.enums.Constant;
import com.bookstore.common.enums.OrderStatus;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.BookService;
import com.bookstore.common.service.OrderService;
import com.bookstore.modules.order.dto.OrderItemDto;
import com.bookstore.modules.order.request.OrderItemRequest;
import com.bookstore.modules.order.request.OrderRequest;
import com.bookstore.modules.order.request.OrderUpdateRequest;
import com.bookstore.modules.order.service.OrderModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final BookService bookService;
    private final OrderModuleService orderModuleService;

    // change
    @PostMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> CreateNewOrder(@Valid @RequestBody OrderRequest orderRequest ){
        Order order = orderService.saveOrder(Order.builder()
                .date(LocalDate.now())
                .totalPrice(orderRequest.getTotalPrice())
                .DeliveryAddress(orderRequest.getDeliveryAddress())
                .orderStatus(OrderStatus.ORDER_PLACEMENT)
                .build());
        orderService.updateOrder(order);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> UpdateOrder(@Valid @RequestBody OrderUpdateRequest orderUpdateRequest){
        Order order = orderService.retrieveById(orderUpdateRequest.getOrderId());
        order.setOrderStatus(orderUpdateRequest.getOrderStatus());
        orderService.updateOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> DeleteOrder(@RequestParam Integer orderId){
        Order order = orderService.retrieveById(orderId);
        orderService.deleteOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = {Uri.ORDERS_ORDER_ITEMS})
    public ResponseEntity<?> RetrieveAllOrderItemsForOrder(@RequestParam Integer orderId){
        List<OrderItemDto> orderItemDtos = orderModuleService.convertToListOrderItem(
                orderService.retrieveOrderItemsByOrderId(orderId));
        return new ResponseEntity<>(orderItemDtos, HttpStatus.OK);
    }
}

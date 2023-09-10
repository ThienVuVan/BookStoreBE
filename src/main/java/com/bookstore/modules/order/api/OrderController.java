package com.bookstore.modules.order.api;

import com.bookstore.common.entity.*;
import com.bookstore.common.enums.Constant;
import com.bookstore.common.enums.OrderStatus;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.BookService;
import com.bookstore.common.service.OrderService;
import com.bookstore.common.service.ShopService;
import com.bookstore.common.service.UserService;
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
    private final UserService userService;
    private final ShopService shopService;

    // change
    @PostMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> CreateNewOrder(@Valid @RequestBody OrderRequest orderRequest ){
        Order order = orderService.saveOrder(Order.builder()
                .date(LocalDate.now())
                .totalPrice(orderRequest.getTotalPrice())
                .DeliveryAddress(orderRequest.getAddress())
                .orderStatus(OrderStatus.ORDER_PLACEMENT)
                .build());
        User user = userService.retrieveUserById(orderRequest.getUserId());
        Shop shop = shopService.retrieveShopById(orderRequest.getShopId());
        order.setUser(user);
        order.setShop(shop);
        orderRequest.getOrderItems().stream().forEach(orderItem -> {
            Book book = bookService.retrieveById(orderItem.getBookId());
            OrderItem orderItem1 = new OrderItem(book, orderItem.getQuantity());
            order.addOrderItem(orderItem1);
        });
        orderService.updateOrder(order);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> UpdateOrder(@RequestParam Integer orderId, @RequestParam String orderStatus){
        Order order = orderService.retrieveById(orderId);
        order.setOrderStatus(orderStatus);
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
        List<OrderItem> orderItems = orderService.retrieveOrderItemsByOrderId(orderId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
}

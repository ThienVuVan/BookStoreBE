package com.bookstore.modules.order.api;

import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.OrderService;
import com.bookstore.modules.order.request.OrderItemRequest;
import com.bookstore.modules.order.request.OrderRequest;
import com.bookstore.modules.order.request.OrderUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> CreateNewOrder(@Valid @RequestBody OrderRequest orderRequest,
                                            @Valid @RequestBody List<OrderItemRequest> orderItemRequests){

        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> UpdateOrder(@RequestParam Integer orderId, @Valid @RequestBody OrderUpdateRequest orderUpdateRequest){
        return null;
    }
    @DeleteMapping(value = {Uri.ORDERS})
    public ResponseEntity<?> DeleteOrder(@RequestParam Integer orderId){
        return null;
    }
    @GetMapping(value = {Uri.ORDERS_ORDER_ITEMS})
    public ResponseEntity<?> RetrieveAllOrderItemsForOrder(@RequestParam Integer orderId){
        return null;
    }
}

package com.bookstore.modules.order.api;

import com.bookstore.common.entity.Order;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping(value = {URI.ORDERS})
    public ResponseEntity<List<Order>> RetrieveAllOrder(){
        return new ResponseEntity<>(orderService.retrieveAllOrder(), HttpStatus.OK);
    }
    @PostMapping(value = {URI.ORDERS})
    public ResponseEntity CreateNewOrder(@Valid @RequestBody Order order, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        orderService.saveOrder(order);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.ORDERS})
    public ResponseEntity UpdateOrder(@Valid @RequestBody Order order, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        orderService.updateOrder(order);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.ORDERS})
    public ResponseEntity DeleteOrder(@Valid @RequestBody Order order, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        orderService.deleteOrder(order);
        return new ResponseEntity(HttpStatus.OK);
    }
}

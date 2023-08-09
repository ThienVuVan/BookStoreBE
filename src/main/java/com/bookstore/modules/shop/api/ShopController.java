package com.bookstore.modules.shop.api;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.Shop;
import com.bookstore.common.enums.URI;
import com.bookstore.common.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    @GetMapping(value = {URI.SHOPS})
    public ResponseEntity<List<Shop>> RetrieveAllShop(){
        return new ResponseEntity<>(shopService.retrieveAllShop(), HttpStatus.OK);
    }
    @PostMapping(value = {URI.SHOPS})
    public ResponseEntity CreateNewShop(@Valid @RequestBody Shop shop, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        shopService.saveShop(shop);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping(value = {URI.SHOPS})
    public ResponseEntity UpdateShop(@Valid @RequestBody Shop shop, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later;
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        shopService.updateShop(shop);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping(value = {URI.SHOPS})
    public ResponseEntity DeleteShop(@Valid @RequestBody Shop shop, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            // update later
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        shopService.deleteShop(shop);
        return new ResponseEntity(HttpStatus.OK);
    }

}

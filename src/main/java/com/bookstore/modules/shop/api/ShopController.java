package com.bookstore.modules.shop.api;

import com.bookstore.common.entity.*;
import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.RoleService;
import com.bookstore.common.service.ShopDetailsService;
import com.bookstore.common.service.ShopService;
import com.bookstore.common.service.UserService;
import com.bookstore.modules.book.dto.BookDto;
import com.bookstore.modules.book.service.BookModuleService;
import com.bookstore.modules.order.dto.OrderDto;
import com.bookstore.modules.order.service.OrderModuleService;
import com.bookstore.modules.shop.dto.ShopDetailsDto;
import com.bookstore.modules.shop.dto.ShopDto;
import com.bookstore.modules.shop.request.*;
import com.bookstore.modules.shop.service.ShopModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final UserService userService;
    private final ShopService shopService;
    private final ShopDetailsService shopDetailsService;
    private final ShopModuleService shopModuleService;
    private final OrderModuleService orderModuleService;
    private final RoleService roleService;
    @GetMapping(value = {Uri.SHOPS_ID})
    public ResponseEntity<?> RetrieveShopById(@RequestParam Integer shopId){
        ShopDto shopDto = shopModuleService.convertToShopDto(shopService.retrieveShopById(shopId));
        return new ResponseEntity<>(shopDto, HttpStatus.OK);
    }
    @GetMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> RetrieveShopByUserId(@RequestParam Integer userId){
        ShopDto shop = shopModuleService.convertToShopDto(shopService.retrieveShopByUserId(userId));
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping(value = {Uri.SHOPS_BOOK_ID})
    public ResponseEntity<?> RetrieveShopByBookId(@RequestParam Integer bookId){
        ShopDto shop = shopModuleService.convertToShopDto(shopService.retrieveShopByBookId(bookId));
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @PostMapping (value = {Uri.SHOPS})
    public ResponseEntity<?> CreateShopForUser(@Valid @ModelAttribute ShopRequest shopRequest){
        // set sh
        String fileName = "image_" + System.currentTimeMillis() + shopRequest.getShopLogo().getOriginalFilename();
        String imagePath = "D:/Projects/BookStoreFE/public/images/" + fileName;
        try {
            shopRequest.getShopLogo().transferTo(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Shop shop = shopService.saveShop(
            Shop.builder()
                    .shopLogoPath(imagePath)
                    .shopName(shopRequest.getShopName())
                    .shopAddress(shopRequest.getShopAddress())
                    .contactPhone(shopRequest.getContactPhone())
                    .contactEmail(shopRequest.getContactEmail())
                    .build()
        );
        ShopDetails shopDetails = shopDetailsService.saveShopDetails(
            ShopDetails.builder()
                    .description(shopRequest.getDescription())
                    .operationHours(shopRequest.getOperationHours())
                    .shippingPolicy(shopRequest.getShippingPolicy())
                    .returnPolicy(shopRequest.getReturnPolicy())
                    .build()
        ) ;
        // add shopDetails
        shop.addShopDetails(shopDetails);
        shopService.updateShop(shop);
        // add shop for user
        User user = userService.retrieveUserById(shopRequest.getUserId());
        user.addShop(shop);
        // add role for user
        Role role = roleService.retrieveByName("ROLE_SHOP").get();
        user.addRole(role);
        userService.updateUser(user);
        // get role return for fe;
        List<String> roles = roleService.retrieveRoleByUserName(user.getUsername())
                .stream().map(role1 -> new String(role1.getName())).collect(Collectors.toList());
        return new ResponseEntity<>(roles,HttpStatus.CREATED);
    }

    @PutMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> UpdateShopForUser(@Valid @ModelAttribute ShopUpdateRequest shopUpdateRequest ){
        // get shop
        Shop shop = shopService.retrieveShopByUserId(shopUpdateRequest.getUserId());
        if(shopUpdateRequest.getShopLogo() != null){
            String fileName = "image_" + System.currentTimeMillis() + shopUpdateRequest.getShopLogo().getOriginalFilename();
            String imagePath = "D:/Projects/BookStoreFE/public/images/" + fileName;
            try {
                shopUpdateRequest.getShopLogo().transferTo(new File(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            shop.setShopLogoPath(imagePath);
        }
        shop.setShopName(shopUpdateRequest.getShopName());
        shop.setShopAddress(shopUpdateRequest.getShopAddress());
        shop.setContactPhone(shopUpdateRequest.getContactPhone());
        shop.setContactEmail(shopUpdateRequest.getContactEmail());
        shopService.updateShop(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> DeleteShopByUserId(@RequestParam Integer userId, @RequestParam Integer shopId){
        // set shop for user
        User user = userService.retrieveUserById(userId);
        user.setShop(null);
        userService.updateUser(user);
        // delete user role
        userService.deleteRoleForUser(userId, 2);
        // delete shop
        Shop shop = shopService.retrieveShopById(shopId);
        shopService.deleteShop(shop);
        // update role
        List<String> roles = roleService.retrieveRolesByUserId(userId)
                .stream().map(role -> new String(role.getName())).collect(Collectors.toList());
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    /* <------------------ Uri.SHOPS_DETAILS ------------------> */
    @GetMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> RetrieveShopDetailForShop(@RequestParam Integer shopId){
        ShopDetailsDto shopDetailsDto = shopModuleService.convertToShopDetailsDto(shopService.retrieveShopDetailsByShopId(shopId));
        return new ResponseEntity<>(shopDetailsDto, HttpStatus.OK);
    }

    @PutMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> UpdateShopDetailForShop(@RequestParam Integer shopId, @Valid @RequestBody ShopDetailsUpdateRequest shopDetailsUpdateRequest){
        ShopDetails shopDetails = shopService.retrieveShopDetailsByShopId(shopId);
        shopDetails.setDescription(shopDetailsUpdateRequest.getDescription());
        shopDetails.setOperationHours(shopDetailsUpdateRequest.getOperationHours());
        shopDetails.setShippingPolicy(shopDetailsUpdateRequest.getShippingPolicy());
        shopDetails.setReturnPolicy(shopDetailsUpdateRequest.getReturnPolicy());
        shopDetailsService.updateShopDetails(shopDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* <-------------------- Uri.SHOPS_ORDERS ---------------------> */
    @PostMapping(value = {Uri.SHOPS_ORDERS})
    public ResponseEntity<?> RetrieveOrdersForShop(@RequestParam Integer shopId, @Valid @RequestBody OrderSearchRequest orderSearchRequest) {
        List<OrderDto> orderDtos = orderModuleService.OrderToOrderDto(
                shopService.retrieveOrdersByCondition(
                        shopId,
                        orderSearchRequest.getId(),
                        orderSearchRequest.getDate(),
                        orderSearchRequest.getTotalPrice(),
                        orderSearchRequest.getDeliveryAddress(),
                        orderSearchRequest.getOrderStatus()));

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }
}

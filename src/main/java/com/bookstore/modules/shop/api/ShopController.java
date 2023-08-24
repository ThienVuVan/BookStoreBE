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
    private final BookModuleService bookModuleService;
    private final RoleService roleService;
    // non - test
    @GetMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> RetrieveShopByUserId(@RequestParam Integer userId){
        ShopDto shop = shopModuleService.convertToShopDto(shopService.retrieveShopByUserId(userId));
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    // non - test
    @PostMapping (value = {Uri.SHOPS})
    public ResponseEntity<?> CreateShopForUser(@RequestBody(required = false) MultipartFile shopLogo, @Valid @RequestBody ShopRequest shopRequest){
        String imagePath = null;
        if(shopLogo != null){
            String fileName = "image_" + System.currentTimeMillis() + shopLogo.getOriginalFilename();
            imagePath = "D:/Projects/BookStoreImages/" + fileName;
            try {
                shopLogo.transferTo(new File(imagePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Shop shop = Shop.builder()
                .shopLogoPath(imagePath)
                .shopName(shopRequest.getShopName())
                .shopAddress(shopRequest.getShopAddress())
                .contactPhone(shopRequest.getContactPhone())
                .contactEmail(shopRequest.getContactEmail())
                .build();
        User user = userService.retrieveUserById(shopRequest.getUserId());
        Shop shop1 = shopService.saveShop(shop);
        user.addShop(shop1);
        Role role = roleService.retrieveByName("ROLE_SHOP").get();
        user.addRole(role);
        userService.updateUser(user);
        List<String> roles = roleService.retrieveRoleByUserName(user.getUsername())
                .stream().map(role1 -> new String(role1.getName())).collect(Collectors.toList());
        return new ResponseEntity<>(roles,HttpStatus.CREATED);
    }

    // non - test
    @PutMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> UpdateShopForUser(@RequestBody MultipartFile shopLogo, @Valid @RequestBody ShopRequest shopRequest ){
        String fileName = "image_" + System.currentTimeMillis() + shopLogo.getOriginalFilename();
        String imagePath = "D:/Projects/BookStoreImages/" + fileName;
        try {
            shopLogo.transferTo(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Shop shop = shopService.retrieveShopByShopName(shopRequest.getShopName());
        shop.setShopLogoPath(imagePath);
        shop.setShopName(shopRequest.getShopName());
        shop.setShopAddress(shopRequest.getShopAddress());
        shop.setContactPhone(shopRequest.getContactPhone());
        shop.setContactEmail(shopRequest.getContactEmail());
        shopService.updateShop(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // non - test
    @DeleteMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> DeleteShopByUserId(@RequestParam Integer userId){
        Shop shop = shopService.retrieveShopByUserId(userId);
        shopService.deleteShop(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /* <------------------ Uri.SHOPS_DETAILS ------------------> */
    // non - test
    @GetMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> RetrieveShopDetailForShop(@RequestParam Integer shopId){
        ShopDetailsDto shopDetailsDto = shopModuleService.convertToShopDetailsDto(shopService.retrieveShopDetailsByShopId(shopId));
        return new ResponseEntity<>(shopDetailsDto, HttpStatus.OK);
    }
    @PostMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> CreateShopDetailForShop(@RequestParam Integer shopId, @Valid @RequestBody ShopDetailsRequest shopDetailsRequest){
        Shop shop = shopService.retrieveShopById(shopId);
        ShopDetails shopDetails = shopDetailsService.saveShopDetails(ShopDetails.builder()
                .description(shopDetailsRequest.getDescription())
                .operationHours(shopDetailsRequest.getOperationHours())
                .shippingPolicy(shopDetailsRequest.getShippingPolicy())
                .returnPolicy(shopDetailsRequest.getReturnPolicy())
                .build());
        shop.addShopDetails(shopDetails);
        shopService.updateShop(shop);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> UpdateShopDetailForShop(@RequestParam Integer shopId, @Valid @RequestBody ShopDetailsRequest shopDetailsRequest){
        ShopDetails shopDetails = shopService.retrieveShopDetailsByShopId(shopId);
        shopDetails.setDescription(shopDetailsRequest.getDescription());
        shopDetails.setOperationHours(shopDetailsRequest.getOperationHours());
        shopDetails.setShippingPolicy(shopDetailsRequest.getShippingPolicy());
        shopDetails.setReturnPolicy(shopDetailsRequest.getReturnPolicy());
        shopDetailsService.updateShopDetails(shopDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> DeleteShopDetailForShop(@RequestParam Integer shopId){
        ShopDetails shopDetails = shopService.retrieveShopDetailsByShopId(shopId);
        shopDetailsService.deleteShopDetails(shopDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /* <-------------------- Uri.SHOPS_ORDERS ---------------------> */
    @GetMapping(value = {Uri.SHOPS_ORDERS})
    public ResponseEntity<?> RetrieveOrdersForShop(@RequestParam Integer shopId, @Valid @RequestBody OrderSearchRequest orderSearchRequest){
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

    /* <------------------ Uri.SHOPS_BOOK ------------------> */
    @GetMapping(value = {Uri.SHOPS_BOOK})
    public ResponseEntity<?> RetrieveBooksForShop(@RequestParam Integer shopId, @RequestParam BookSearchRequest bookSearchRequest){
        List<BookDto> bookDtos = bookModuleService.convertToListBookDto(
                shopService.retrieveBooksByCondition(
                        shopId,
                        bookSearchRequest.getTitle(),
                        bookSearchRequest.getPrice(),
                        bookSearchRequest.getCurrentQuantity(),
                        bookSearchRequest.getSoldQuantity()));
        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }
}

package com.bookstore.modules.shop.api;

import com.bookstore.common.enums.Uri;
import com.bookstore.common.service.ShopService;
import com.bookstore.modules.shop.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> RetrieveShopByUserId(@RequestParam Integer userId){
        return null;
    }

    @PostMapping (value = {Uri.SHOPS})
    public ResponseEntity<?> CreateShopForUser(@RequestParam Integer userId, @RequestBody MultipartFile shopLogo, @Valid @RequestBody ShopRequest shopRequest){
        return null;
    }

    @PutMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> UpdateShopForUser(@RequestParam Integer userId, @RequestBody MultipartFile shopLogo,@Valid @RequestBody ShopRequest shopRequest ){
        return null;
    }

    @DeleteMapping(value = {Uri.SHOPS})
    public ResponseEntity<?> DeleteShopByUserId(@RequestParam Integer userId){
        return null;
    }


    /* <------------------ Uri.SHOPS_DETAILS ------------------> */
    @GetMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> RetrieveShopDetailForShop(@RequestParam Integer shopId){
        return null;
    }
    @PostMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> CreateShopDetailForShop(@RequestParam Integer shopId, @Valid @RequestBody ShopDetailsRequest shopDetailsRequest){
        return null;
    }
    @PutMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> UpdateShopDetailForShop(@RequestParam Integer shopId, @Valid @RequestBody ShopDetailsRequest shopDetailsRequest){
        return null;
    }
    @DeleteMapping(value = {Uri.SHOPS_DETAILS})
    public ResponseEntity<?> DeleteShopDetailForShop(@RequestParam Integer shopId){
        return null;
    }


    /* <-------------------- Uri.SHOPS_ORDERS ---------------------> */
    @GetMapping(value = {Uri.SHOPS_ORDERS})
    public ResponseEntity<?> RetrieveOrdersForShop(@RequestParam Integer shopId, @Valid @RequestBody OrderSearchRequest orderSearchRequest){
        return null;
    }

    /* <------------------ Uri.SHOPS_BOOK ------------------> */
    @GetMapping(value = {Uri.SHOPS_BOOK})
    public ResponseEntity<?> RetrieveBooksForShop(@RequestParam Integer shopId, @RequestParam BookSearchRequest bookSearchRequest){
        return null;
    }
}

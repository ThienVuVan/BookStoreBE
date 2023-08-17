package com.bookstore.modules.shop.service;

import com.bookstore.common.entity.Shop;
import com.bookstore.common.entity.ShopDetails;
import com.bookstore.modules.shop.dto.ShopDetailsDto;
import com.bookstore.modules.shop.dto.ShopDto;
import com.bookstore.modules.shop.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopModuleService {
    private  final ShopMapper shopMapper;
    public ShopDto convertToShopDto(Shop shop){
        return shopMapper.shopToShopDto(shop);
    }
    public ShopDetailsDto convertToShopDetailsDto(ShopDetails shopDetails){
        return shopMapper.shopDetailsToshopDetailsDto(shopDetails);
    }
}

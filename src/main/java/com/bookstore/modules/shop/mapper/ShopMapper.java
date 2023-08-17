package com.bookstore.modules.shop.mapper;

import com.bookstore.common.entity.Shop;
import com.bookstore.common.entity.ShopDetails;
import com.bookstore.modules.shop.dto.ShopDetailsDto;
import com.bookstore.modules.shop.dto.ShopDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopDto shopToShopDto(Shop shop);
    ShopDetailsDto shopDetailsToshopDetailsDto(ShopDetails shopDetails);
}

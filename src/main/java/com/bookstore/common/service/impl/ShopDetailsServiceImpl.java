package com.bookstore.common.service.impl;

import com.bookstore.common.entity.ShopDetails;
import com.bookstore.common.repository.ShopDetailsRepository;
import com.bookstore.common.service.ShopDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopDetailsServiceImpl implements ShopDetailsService {
    private final ShopDetailsRepository shopDetailsRepository;
    @Override
    public ShopDetails saveShopDetails(ShopDetails shopDetails) {
        return shopDetailsRepository.save(shopDetails);
    }

    @Override
    public ShopDetails updateShopDetails(ShopDetails shopDetails) {
        return shopDetailsRepository.save(shopDetails);
    }
}

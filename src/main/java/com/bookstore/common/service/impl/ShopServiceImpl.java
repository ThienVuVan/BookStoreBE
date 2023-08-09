package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Shop;
import com.bookstore.common.repository.ShopRepository;
import com.bookstore.common.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    @Override
    public List<Shop> retrieveAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void deleteShop(Shop shop) {
        shopRepository.delete(shop);
    }
}

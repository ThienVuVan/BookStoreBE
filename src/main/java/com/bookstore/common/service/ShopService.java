package com.bookstore.common.service;
import com.bookstore.common.entity.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> retrieveAllShop();
    public Shop saveShop(Shop shop);
    public Shop updateShop(Shop shop);
    public void deleteShop(Shop shop);
}

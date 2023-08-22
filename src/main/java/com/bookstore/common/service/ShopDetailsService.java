package com.bookstore.common.service;

import com.bookstore.common.entity.ShopDetails;

public interface ShopDetailsService {
    public ShopDetails saveShopDetails(ShopDetails shopDetails);
    public ShopDetails updateShopDetails(ShopDetails shopDetails);
    public void deleteShopDetails(ShopDetails shopDetails);
}

package com.bookstore.common.service;
import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.Shop;
import com.bookstore.common.entity.ShopDetails;

import java.time.LocalDate;
import java.util.List;

public interface ShopService {
    public Shop saveShop(Shop shop);
    public Shop updateShop(Shop shop);
    public void deleteShop(Shop shop);
    public Shop retrieveShopById(Integer id);
    public Shop retrieveShopByUserId(Integer id);
    public Shop retrieveShopByBookId(Integer bookId);
    public ShopDetails retrieveShopDetailsByShopId(Integer id);
    public List<Order> retrieveOrdersByCondition(Integer shopId, Integer orderId, LocalDate date,
                                                 Double totalPrice, String DeliveryAddress, String orderStatus);

}

package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.Shop;
import com.bookstore.common.entity.ShopDetails;
import com.bookstore.common.repository.ShopRepository;
import com.bookstore.common.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public Shop retrieveShopById(Integer id) {
        return shopRepository.findShopById(id);
    }

    @Override
    public Shop retrieveShopByUserId(Integer id) {
        return shopRepository.findShopByUserId(id);
    }

    @Override
    public Shop retrieveShopByBookId(Integer bookId) {
        return shopRepository.findShopByBookId(bookId);
    }

    @Override
    public Shop retrieveShopByShopName(String name) {
        return shopRepository.findShopByShopName(name);
    }

    @Override
    public ShopDetails retrieveShopDetailsByShopId(Integer id) {
        return shopRepository.findShopDetailsByShopId(id);
    }

    @Override
    public List<Order> retrieveOrdersByCondition(Integer shopId, Integer orderId, LocalDate date, Double totalPrice, String DeliveryAddress, String orderStatus) {
        return shopRepository.findOrdersForShop(shopId, orderId, date, totalPrice, DeliveryAddress, orderStatus);
    }

    @Override
    public List<Book> retrieveBooksByCondition(Integer shopId, String title, Double price, Integer currentQuantity, Integer soldQuantity) {
        return shopRepository.findBooksForShop(shopId, title, price, currentQuantity, soldQuantity);
    }
}

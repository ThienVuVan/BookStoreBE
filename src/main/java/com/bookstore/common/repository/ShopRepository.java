package com.bookstore.common.repository;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.Shop;
import com.bookstore.common.entity.ShopDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Query("from Shop s where s.id = :id")
    Shop findShopById(Integer id);
    Shop findShopByUserId(Integer id);
    Shop findShopByShopName(String name);
    @Query("select d, s from Shop s join fetch s.shopDetails d where s.id = :id")
    ShopDetails findShopDetailsByShopId(Integer id);

    @Query("""
            select o, s from Shop s join fetch s.orders o where (s.id = :shopId)
            and (o.id = :orderId or :orderId is null)
            and (o.date = :date or :date is null)
            and (o.totalPrice = :totalPrice or :totalPrice is null)
            and (o.DeliveryAddress = :DeliveryAddress or :DeliveryAddress is null)
            and (o.orderStatus = :orderStatus or :orderStatus is null)
           """)
    List<Order> findOrdersForShop(Integer shopId, Integer orderId,LocalDate date,
                                  Double totalPrice, String DeliveryAddress, Boolean orderStatus);

    @Query("""
            select b, s from Shop s join fetch s.books b where (s.id = :shopId)
            and (b.title = :title or :title is null)
            and (b.price = :price or :price is null)
            and (b.currentQuantity = :currentQuantity or :currentQuantity is null)
            and (b.soldQuantity = :soldQuantity or :soldQuantity is null)
           """)
    List<Book> findBooksForShop(Integer shopId, String title, Double price, Integer currentQuantity, Integer soldQuantity);
}

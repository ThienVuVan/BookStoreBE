package com.bookstore.common.repository;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByPhoneNumber(String phoneNumber);
//    @Query("select o, u from User u join fetch u.orders o where u.id = :id")
    @Query("select o from Order o where o.user.id = :id")
    List<Order> findOrderByUserId(Integer id);

}

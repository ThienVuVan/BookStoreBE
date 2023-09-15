package com.bookstore.common.repository;

import com.bookstore.common.entity.Order;
import com.bookstore.common.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsUserBySocialId(String socialId);
//    @Query("select o, u from User u join fetch u.orders o where u.id = :id")
    @Query("select o from Order o where o.user.id = :id")
    List<Order> findOrderByUserId(Integer id);
    @Transactional
    @Modifying
    @Query(value = "delete from users_roles ur where ur.user_id = :userId and ur.role_id = :roleId ", nativeQuery = true)
    void deleteRoleForUser(Integer userId, Integer roleId);

}

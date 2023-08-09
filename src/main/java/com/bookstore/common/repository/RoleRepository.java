package com.bookstore.common.repository;

import com.bookstore.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    @Query("select r from Role r join fetch r.users u where u.username = :username")
    List<Role> findRoleByUserName(String username);
}

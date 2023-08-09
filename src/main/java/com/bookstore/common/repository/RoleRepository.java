package com.bookstore.common.repository;

import com.bookstore.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    @Query("select r from Role r join fetch r.users u where u.username = :username")
    List<Role> findRoleByUserName(String username);
}

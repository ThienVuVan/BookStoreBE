package com.bookstore.common.repository;

import com.bookstore.common.entity.ShopDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopDetailsRepository extends JpaRepository<ShopDetails, Integer> {
}

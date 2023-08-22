package com.bookstore.common.repository;

import com.bookstore.common.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findReviewById(Integer id);
    Page<Review> findReviewByBookId(Integer bookId, Pageable pageable);
}

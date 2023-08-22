package com.bookstore.common.repository;

import com.bookstore.common.entity.Rate;
import com.bookstore.common.entity.compositekey.UserBookKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Integer> {
    Rate findRateById(UserBookKey id);
    Integer countRateByBookIdAndRating(Integer bookId, Integer rate);
}

package com.bookstore.common.service;

import com.bookstore.common.entity.Rate;
import com.bookstore.common.entity.compositekey.UserBookKey;

import java.util.List;

public interface RateService {
    public Rate saveRate(Rate rate);
    public Integer countRateByBookIdAndRating(Integer bookId, Integer rating);
}

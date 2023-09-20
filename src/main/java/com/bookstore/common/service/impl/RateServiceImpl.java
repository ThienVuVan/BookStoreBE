package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Rate;
import com.bookstore.common.entity.compositekey.UserBookKey;
import com.bookstore.common.repository.RateRepository;
import com.bookstore.common.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;
    @Override
    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Integer countRateByBookIdAndRating(Integer bookId, Integer rating) {
        return rateRepository.countRateByBookIdAndRating(bookId, rating);
    }
}

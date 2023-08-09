package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Rate;
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
    public List<Rate> retrieveAllRate() {
        return rateRepository.findAll();
    }

    @Override
    public Rate saveRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Rate updateRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public void deleteRate(Rate rate) {
        rateRepository.delete(rate);
    }
}

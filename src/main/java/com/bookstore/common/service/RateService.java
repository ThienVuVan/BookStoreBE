package com.bookstore.common.service;

import com.bookstore.common.entity.Rate;

import java.util.List;

public interface RateService {
    public List<Rate> retrieveAllRate();
    public Rate saveRate(Rate rate);
    public Rate updateRate(Rate rate);
    public void deleteRate(Rate rate);
}

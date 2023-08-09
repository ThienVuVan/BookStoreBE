package com.bookstore.common.service;


import com.bookstore.common.entity.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> retrieveAllReview();
    public Review saveReview(Review review);
    public Review updateReview(Review review);
    public void deleteReview(Review review);
}

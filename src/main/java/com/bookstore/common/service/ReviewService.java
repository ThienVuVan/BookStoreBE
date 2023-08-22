package com.bookstore.common.service;


import com.bookstore.common.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    public List<Review> retrieveAllReview();
    public Review saveReview(Review review);
    public Review updateReview(Review review);
    public void deleteReview(Review review);
    public Review retrieveReviewById(Integer id);
    public Page<Review> retrieveReviewsByPage(Integer bookId, Pageable pageable);
}

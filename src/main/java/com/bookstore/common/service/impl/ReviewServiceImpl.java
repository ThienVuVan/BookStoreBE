package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Review;
import com.bookstore.common.repository.ReviewRepository;
import com.bookstore.common.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    @Override
    public List<Review> retrieveAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public Review retrieveReviewById(Integer id) {
        return reviewRepository.findReviewById(id);
    }

    @Override
    public Page<Review> retrieveReviewsByPage(Integer bookId, Pageable pageable) {
        return reviewRepository.findReviewByBookId(bookId, pageable);
    }
}

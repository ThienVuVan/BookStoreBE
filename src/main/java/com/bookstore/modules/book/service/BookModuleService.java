package com.bookstore.modules.book.service;

import com.bookstore.common.entity.*;
import com.bookstore.common.service.RateService;
import com.bookstore.modules.book.dto.*;
import com.bookstore.modules.book.mapper.*;
import com.bookstore.modules.category.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookModuleService {
    private final BookMapper bookMapper;
    private final ReviewMapper reviewMapper;
    private final RateService rateService;

    public BookDto convertToBookDto(Book book){
        return bookMapper.BookToBookDto(book);
    }

    public List<BookDto> convertToListBookDto(List<Book> books){
        return books.stream().map(book -> bookMapper.BookToBookDto(book)).collect(Collectors.toList());
    }
    public List<BookDto> convertPageToListBookDto(Page<Book> page){
        return page.getContent().stream().map(book -> bookMapper.BookToBookDto(book)).collect(Collectors.toList());
    }
    public List<ReviewDto> convertToListReviewDto(Page<Review> reviews){
        List<ReviewDto> reviewDtos = reviews.getContent().stream().map(review ->
            reviewMapper.ReviewToDto(review)
        ).collect(Collectors.toList());
        var wrapper = new Object(){ Integer ordinal = 0; };
        reviews.stream().forEach(review -> {
            reviewDtos.get(wrapper.ordinal).setUsername(review.getUser().getUsername());
            wrapper.ordinal++;
        });
        return reviewDtos;
    }

    public RateDto convertToRateDto(Integer bookId){
        Integer five = rateService.countRateByBookIdAndRating(bookId, 5);
        Integer four = rateService.countRateByBookIdAndRating(bookId, 4);
        Integer three = rateService.countRateByBookIdAndRating(bookId, 3);
        Integer two = rateService.countRateByBookIdAndRating(bookId, 2);
        Integer one = rateService.countRateByBookIdAndRating(bookId, 1);
        return new RateDto(five, four, three, two, one);
    }
}

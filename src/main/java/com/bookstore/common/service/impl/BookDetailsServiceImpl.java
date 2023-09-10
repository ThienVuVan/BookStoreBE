package com.bookstore.common.service.impl;

import com.bookstore.common.entity.BookDetails;
import com.bookstore.common.repository.BookDetailsRepository;
import com.bookstore.common.service.BookDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDetailsServiceImpl implements BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;
    @Override
    public BookDetails saveBookDetails(BookDetails bookDetails) {
        return bookDetailsRepository.save(bookDetails);
    }

    @Override
    public BookDetails updateBookDetails(BookDetails bookDetails) {
        return bookDetailsRepository.save(bookDetails);
    }
}

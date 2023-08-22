package com.bookstore.common.service.impl;

import com.bookstore.common.entity.BookImage;
import com.bookstore.common.repository.BookImageRepository;
import com.bookstore.common.service.BookImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookImageServiceImpl implements BookImageService {
    private final BookImageRepository bookImageRepository;

    @Override
    public BookImage saveBookImage(BookImage bookImage) {
        return bookImageRepository.save(bookImage);
    }

    @Override
    public void deleteBookImage(BookImage bookImage) {
        bookImageRepository.delete(bookImage);
    }
}

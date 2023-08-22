package com.bookstore.common.service;

import com.bookstore.common.entity.BookImage;

public interface BookImageService {
    public BookImage saveBookImage(BookImage bookImage);
    public void deleteBookImage(BookImage bookImage);
}

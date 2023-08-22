package com.bookstore.common.service;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.BookDetails;

public interface BookDetailsService {
    public BookDetails saveBookDetails(BookDetails bookDetails);
    public BookDetails updateBookDetails(BookDetails bookDetails);
    public void deleteBookDetails(BookDetails bookDetails);
}

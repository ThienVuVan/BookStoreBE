package com.bookstore.common.service;

import com.bookstore.common.entity.Book;

import java.util.List;


public interface BookService {
    public List<Book> retrieveAllBooks();
    public Book saveBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public Book retrieveByTitle(String title);
}

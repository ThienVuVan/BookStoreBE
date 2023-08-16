package com.bookstore.common.service;

import com.bookstore.common.entity.BookImage;
import com.bookstore.modules.book.dto.BookDto;
import com.bookstore.common.entity.Book;

import java.util.List;


public interface BookService {
    public List<BookDto> retrieveAllBooks();
    public Book saveBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public Book retrieveByTitle(String title);
    public Book retrieveById(Integer id);
//    public List<BookImage> retrieveBookImagesByBookId(Integer id);

}

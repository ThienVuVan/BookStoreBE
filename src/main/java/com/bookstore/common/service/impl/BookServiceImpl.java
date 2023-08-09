package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Book;
import com.bookstore.common.repository.BookRepository;
import com.bookstore.common.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public List<Book> retrieveAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book retrieveByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}

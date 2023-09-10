package com.bookstore.common.service.impl;

import com.bookstore.common.entity.BookDetails;
import com.bookstore.common.entity.BookImage;
import com.bookstore.modules.book.dto.BookDto;
import com.bookstore.common.entity.Book;
import com.bookstore.common.repository.BookRepository;
import com.bookstore.common.service.BookService;
import com.bookstore.modules.book.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
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

    @Override
    public Book retrieveById(Integer id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Page<Book> retrieveBooksByPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> retrieveBookByCondition(String title, String authors, Double lowPrice, Double highPrice, String category) {
        return bookRepository.findBookByCondition(title, authors, lowPrice, highPrice, category);
    }

    @Override
    public BookDetails retrieveBookDetailsByBookId(Integer id) {
        return bookRepository.findBookDetailsByBookId(id);
    }

    @Override
    public List<BookImage> retrieveBookImagesByBookId(Integer id) {
        return bookRepository.findBookImagesByBookId(id);
    }

    @Override
    public List<Book> retrieveBookByShopId(Integer shopId) {
        return bookRepository.findBookByShopId(shopId);
    }

    @Override
    public void deleteBookCategory(Integer bookId) {
        bookRepository.deleteBookCategory(bookId);
    }
}

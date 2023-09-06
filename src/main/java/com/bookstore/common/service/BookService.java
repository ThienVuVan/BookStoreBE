package com.bookstore.common.service;

import com.bookstore.common.entity.Author;
import com.bookstore.common.entity.BookDetails;
import com.bookstore.common.entity.BookImage;
import com.bookstore.modules.book.dto.BookDto;
import com.bookstore.common.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookService {
    public List<BookDto> retrieveAllBooks();
    public Book saveBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public Book retrieveByTitle(String title);
    public Book retrieveById(Integer id);
//    public List<BookImage> retrieveBookImagesByBookId(Integer id);
    public Page<Book> retrieveBooksByPage(Pageable pageable);
    public List<Book> retrieveBookByCondition(String title, Double price, String category);
    public BookDetails retrieveBookDetailsByBookId(Integer id);
    public List<BookImage> retrieveBookImagesByBookId(Integer id);
    public List<Author> retrieveAuthorsByBookId(Integer id);
    public List<Book> retrieveBookByShopId(Integer shopId);
    public void deleteBookCategory(Integer bookId);

}

package com.bookstore.common.repository;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    Book findBookById(Integer id);
    @Query("select i, b from Book b join fetch b.bookImages i where b.id = :id")
    List<BookImage> findBookImageByBookId(Integer id);
}

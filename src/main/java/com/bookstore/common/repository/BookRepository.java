package com.bookstore.common.repository;

import com.bookstore.common.entity.Author;
import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.BookDetails;
import com.bookstore.common.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    Book findBookById(Integer id);
    @Query("select i, b from Book b join fetch b.bookImages i where b.id = :id")
    List<BookImage> findBookImageByBookId(Integer id);
    @Query("""
            select b from Book b join fetch b.categories c where 
            (b.title = :title or :title is null)
            and (b.price = :price or :price is null)
            and (c.name = :category or :category is null)
           """)
    List<Book> findBookByCondition(String title, Double price, String category);
    @Query("select b from BookDetails b where b.book.id = :id")
    BookDetails findBookDetailsByBookId(Integer id);
    @Query("select bimg from BookImage bimg where bimg.book.id = :id")
    List<BookImage> findBookImagesByBookId(Integer id);
    @Query("select a from Author a join fetch a.books b where b.id = :id")
    List<Author> findAuthorsByBookId(Integer id);
}

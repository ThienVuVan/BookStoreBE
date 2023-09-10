package com.bookstore.common.repository;

import com.bookstore.common.entity.Book;
import com.bookstore.common.entity.BookDetails;
import com.bookstore.common.entity.BookImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    Book findBookById(Integer id);
    List<Book> findBookByShopId(Integer shopId);
    @Query("""
           SELECT b FROM Book b JOIN FETCH b.categories c WHERE
           (:title IS NULL OR b.title LIKE CONCAT('%', :title, '%'))
           AND (:authors IS NULL OR b.author LIKE CONCAT('%', :authors, '%'))
           AND (:lowPrice IS NULL OR b.price >= :lowPrice)
           AND (:highPrice IS NULL OR b.price <= :highPrice)
           AND (:category IS NULL OR c.name = :category)
           """)
    List<Book> findBookByCondition(String title, String authors, Double lowPrice, Double highPrice, String category);
    @Query("select b from BookDetails b where b.book.id = :id")
    BookDetails findBookDetailsByBookId(Integer id);
    @Query("select bimg from BookImage bimg where bimg.book.id = :id")
    List<BookImage> findBookImagesByBookId(Integer id);
    @Modifying
    @Transactional
    @Query(value = "delete from books_categories bc where bc.book_id = :bookId", nativeQuery = true)
    void deleteBookCategory(Integer bookId);
}

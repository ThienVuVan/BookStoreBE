package com.bookstore.common.repository;

import com.bookstore.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.parentId is null")
    List<Category> findAllParentCategory();
    Category findCategoryByName(String name);
    List<Category> findCategoriesByParentId(Integer parentId);
    @Query("select c from Category c join fetch c.books b where b.id = :bookId")
    List<Category> findCategoriesByBooksId(Integer bookId);
    @Query(value = "delete from books_categories bc where bc.book_id = :bookId", nativeQuery = true)
    void deleteBookCategoriesByBookId(@Param("bookId") Integer bookId);
    Category findCategoryById(Integer id);
}

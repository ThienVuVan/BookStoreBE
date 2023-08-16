package com.bookstore.common.repository;

import com.bookstore.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.parentId is null")
    List<Category> findAllParentCategory();
    Category findCategoryByName(String name);
    List<Category> findCategoriesByParentId(Integer parentId);
}

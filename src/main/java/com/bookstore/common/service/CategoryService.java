package com.bookstore.common.service;

import com.bookstore.common.entity.Category;
import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Category category);
    public List<Category> retrieveAllParentCategory();
    public Category retrieveByCategoryName(String name);
    public List<Category> retrieveCategoriesByParentId(Integer parentId);
    Category retrieveById(Integer id);
}

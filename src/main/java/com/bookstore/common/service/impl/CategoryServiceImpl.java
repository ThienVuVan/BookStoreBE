package com.bookstore.common.service.impl;

import com.bookstore.common.entity.Category;
import com.bookstore.common.repository.CategoryRepository;
import com.bookstore.common.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> retrieveAllParentCategory() {
        return categoryRepository.findAllParentCategory();
    }

    @Override
    public Category retrieveByCategoryName(String name) {
        return categoryRepository.findCategoryByName(name);

    }
    @Override
    public List<Category> retrieveCategoriesByParentId(Integer parentId) {
        return categoryRepository.findCategoriesByParentId(parentId);
    }
    @Override
    public Category retrieveById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> retrieveAllCategories() {
        return categoryRepository.findAll();
    }
}

package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.repository.CategoryRepository;
import io.hrnugr.sample.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category readCategory(String categoryName) {
        return categoryRepository.getByCategoryName(categoryName);
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category get(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void update(Long categoryId, Category category) {
        Category catInDB = categoryRepository.findById(categoryId).orElseThrow();
        catInDB.setCategoryName(category.getCategoryName());
        catInDB.setDescription(category.getDescription());
        catInDB.setImageUrl(category.getImageUrl());
        categoryRepository.save(catInDB);
    }
}

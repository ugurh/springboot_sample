package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.repository.CategoryRepository;
import io.hrnugr.sample.service.CategoryService;
import org.springframework.stereotype.Service;

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
}

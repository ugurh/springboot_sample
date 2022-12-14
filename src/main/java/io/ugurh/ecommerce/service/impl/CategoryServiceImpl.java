package io.ugurh.ecommerce.service.impl;

import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.model.entity.Category;
import io.ugurh.ecommerce.repository.CategoryRepository;
import io.ugurh.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author harun ugur
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
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
        Category catInDb = categoryRepository.findById(categoryId).orElseThrow();
        catInDb.setCategoryName(category.getCategoryName());
        catInDb.setDescription(category.getDescription());
        catInDb.setImageUrl(category.getImageUrl());
        categoryRepository.save(catInDb);
    }

    @Override
    public void delete(Long categoryId) {
        Category category = get(categoryId);

        if (Objects.isNull(category)) {
            throw new ResourceNotFoundException("category does not exist");
        }

        categoryRepository.deleteCategory(categoryId);
    }
}

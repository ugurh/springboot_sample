package io.hrnugr.ecommerce.service;


import io.hrnugr.ecommerce.model.entity.Category;

import java.util.List;

/**
 * @author harun ugur
 */
public interface CategoryService {
    Category readCategory(String categoryName);

    void createCategory(Category category);

    List<Category> listCategories();

    Category get(Long categoryId);

    void update(Long categoryId, Category category);
}

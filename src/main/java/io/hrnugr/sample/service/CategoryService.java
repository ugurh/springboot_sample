package io.hrnugr.sample.service;


import io.hrnugr.sample.model.Category;

import java.util.List;

public interface CategoryService {
    Category readCategory(String categoryName);


    void createCategory(Category category);

    List<Category> listCategories();

    Category get(Long categoryId);

    void update(Long categoryId, Category category);
}

package io.hrnugr.sample.service;


import io.hrnugr.sample.model.Category;

public interface CategoryService {
    Category readCategory(String categoryName);


    void createCategory(Category category);
}

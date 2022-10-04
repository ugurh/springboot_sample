package io.hrnugr.sample.service;

import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.model.Product;

public interface ProductService {
    void create(Product product, Category category);
}

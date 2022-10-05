package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.model.Product;

import java.util.List;

public interface ProductService {
    void create(Product product, Category category);

    List<Product> listProducts();

    void updateProduct(Long productId, ProductDto productDto, Category category);
}

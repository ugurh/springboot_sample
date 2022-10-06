package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.exceptions.CustomException;

import java.util.List;

public interface ProductService {
    void create(Product product, Category category);

    List<Product> listProducts();

    void updateProduct(Long productId, ProductDto productDto, Category category) throws CustomException;

    Product getById(Long id) throws CustomException;
}

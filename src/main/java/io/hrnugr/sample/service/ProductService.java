package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.handler.exceptions.CustomException;
import io.hrnugr.sample.handler.exceptions.ResourceNotExistException;

import java.util.List;

/**
 * @author harun ugur
 */
public interface ProductService {
    void create(Product product, Category category);

    List<Product> listProducts();

    void updateProduct(Long productId, ProductDto productDto, Category category) throws CustomException;

    Product getById(Long productId) throws CustomException, ResourceNotExistException;
}

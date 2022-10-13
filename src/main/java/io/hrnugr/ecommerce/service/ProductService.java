package io.hrnugr.ecommerce.service;

import io.hrnugr.ecommerce.handler.exceptions.CustomException;
import io.hrnugr.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.hrnugr.ecommerce.model.dto.request.ProductDto;
import io.hrnugr.ecommerce.model.entity.Category;
import io.hrnugr.ecommerce.model.entity.Product;

import java.util.List;

/**
 * @author harun ugur
 */
public interface ProductService {
    void create(Product product, Category category);

    List<Product> listProducts();

    void updateProduct(Long productId, ProductDto productDto, Category category) throws CustomException;

    Product getById(Long productId) throws CustomException, ResourceNotFoundException;
}

package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.handler.exceptions.CustomException;
import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.model.dto.request.ProductDto;
import io.ugurh.ecommerce.model.entity.Category;
import io.ugurh.ecommerce.model.entity.Product;

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

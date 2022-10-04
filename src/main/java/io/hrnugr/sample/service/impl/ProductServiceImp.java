package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.model.Product;
import io.hrnugr.sample.repository.ProductRepository;
import io.hrnugr.sample.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product product, Category category) {
        product.setCategory(category);
        productRepository.save(product);
    }
}

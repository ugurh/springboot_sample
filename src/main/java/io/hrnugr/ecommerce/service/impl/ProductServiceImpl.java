package io.hrnugr.ecommerce.service.impl;

import io.hrnugr.ecommerce.handler.exceptions.CustomException;
import io.hrnugr.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.hrnugr.ecommerce.model.dto.request.ProductDto;
import io.hrnugr.ecommerce.model.entity.Category;
import io.hrnugr.ecommerce.model.entity.Product;
import io.hrnugr.ecommerce.repository.ProductRepository;
import io.hrnugr.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author harun ugur
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(Product product, Category category) {
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Long productId, ProductDto productDto, Category category) throws CustomException {
        Optional<Product> productInDb = productRepository.findById(productId);

        if (productInDb.isEmpty()) {
            throw new CustomException("Product can not exist");
        }

        Product product = productInDb.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public Product getById(Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElse(null);

        if (Objects.isNull(product)) {
            throw new ResourceNotFoundException("Product does not exist");
        }

        return product;
    }
}

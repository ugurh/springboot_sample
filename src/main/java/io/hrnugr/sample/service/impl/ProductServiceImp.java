package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.model.Product;
import io.hrnugr.sample.repository.ProductRepository;
import io.hrnugr.sample.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Long productId, ProductDto productDto, Category category) {
        Optional<Product> productInDB = productRepository.findById(productId);

        if (productInDB.isEmpty())
            throw new RuntimeException("Product can not exist");

        Product product = productInDB.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setCategory(category);

        productRepository.save(product);
    }
}

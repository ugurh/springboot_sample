package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.exceptions.CustomException;
import io.hrnugr.sample.repository.ProductRepository;
import io.hrnugr.sample.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public void updateProduct(Long productId, ProductDto productDto, Category category) throws CustomException {
        Optional<Product> productInDB = productRepository.findById(productId);

        if (productInDB.isEmpty())
            throw new CustomException("Product can not exist");

        Product product = productInDB.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public Product getById(Long id) throws CustomException {
        Product product = productRepository.findById(id).orElse(null);

        if (Objects.isNull(product))
            throw new CustomException("Product does not exist");

        return product;
    }
}

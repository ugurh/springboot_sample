package io.ugurh.ecommerce.mapper.impl;

import io.ugurh.ecommerce.mapper.BaseMapper;
import io.ugurh.ecommerce.model.dto.request.ProductDto;
import io.ugurh.ecommerce.model.entity.Product;
import io.ugurh.ecommerce.repository.CategoryRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author harun ugur
 */
@Configuration
public class ProductMapper implements BaseMapper {

    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto toDto(Object obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        Product model = (Product) obj;
        return ProductDto.builder()
                .productId(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .imageUrl(model.getImageUrl())
                .price(model.getPrice())
                .categoryId(model.getCategory().getId())
                .build();
    }

    @Override
    public Product toEntity(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        ProductDto dto = (ProductDto) obj;

        Product product = new Product();
        product.setId(dto.getProductId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setPrice(dto.getPrice());
        product.setCategory(categoryRepository.getById(dto.getCategoryId()));

        return product;
    }
}

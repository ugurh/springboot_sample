package io.hrnugr.sample.mapper.impl;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.mapper.BaseMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author harun ugur
 */
@Configuration
public class ProductMapper implements BaseMapper {
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

        return product;
    }

    @Override
    public List<ProductDto> toListDto(List list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        return list.stream().map(this::toDto).toList();
    }
}

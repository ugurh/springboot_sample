package io.hrnugr.sample.mapper.impl;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.mapper.BaseMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Configuration
public class ProductMapper implements BaseMapper {
    @Override
    public ProductDto toDto(Object obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        Product model = (Product) obj;
        return ProductDto.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .imageURL(model.getImageURL())
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

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .imageURL(dto.getImageURL())
                .price(dto.getPrice())
                .build();
    }

    @Override
    public List<ProductDto> toListDto(List list) {
        if (list.isEmpty())
            return Collections.emptyList();

        return (List<ProductDto>) list.stream().map(this::toDto).toList();
    }
}

package io.hrnugr.sample.mapper.impl;

import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.mapper.BaseMapper;
import io.hrnugr.sample.model.Product;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class ProductMapper implements BaseMapper {
    @Override
    public  ProductDto toDto(Object obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        Product model = (Product) obj;
        return ProductDto.builder()
                .name(model.getName())
                .description(model.getDescription())
                .imageURL(model.getImageURL())
                .price(model.getPrice())
                .build();
    }

    @Override
    public Product toEntity(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }

        ProductDto dto = (ProductDto) obj;

        return Product.builder()
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

            return (List<ProductDto>) list.stream().map(this::toDto).collect(Collectors.toList());
    }
}

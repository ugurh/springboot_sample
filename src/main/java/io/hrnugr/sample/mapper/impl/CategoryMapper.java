package io.hrnugr.sample.mapper.impl;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.mapper.BaseMapper;
import io.hrnugr.sample.model.Category;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CategoryMapper implements BaseMapper {

    @Override
    public CategoryDto toDto(Category model) {

        if (Objects.isNull(model)) {
            return null;
        }

        return CategoryDto.builder()
                .categoryName(model.getCategoryName())
                .description(model.getDescription())
                .imageUrl(model.getImageUrl())
                .build();
    }

    @Override
    public Category toModel(CategoryDto dto) {

        if (Objects.isNull(dto)) {
            return null;
        }

        return Category.builder()
                .categoryName(dto.getCategoryName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}

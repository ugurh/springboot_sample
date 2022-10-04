package io.hrnugr.sample.mapper.impl;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.mapper.BaseMapper;
import io.hrnugr.sample.model.Category;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<CategoryDto> toListDto(List<Category> categories) {
        if (categories.isEmpty())
            return Collections.emptyList();

        return categories.stream().map(this::toDto).collect(Collectors.toList());
    }
}

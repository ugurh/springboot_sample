package io.hrnugr.sample.mapper.impl;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.mapper.BaseMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Configuration
public class CategoryMapper implements BaseMapper {

    @Override
    public CategoryDto toDto(Object obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        Category model = (Category) obj;
        return CategoryDto.builder()
                .categoryId(model.getId())
                .categoryName(model.getCategoryName())
                .description(model.getDescription())
                .imageUrl(model.getImageUrl())
                .build();
    }

    @Override
    public Category toEntity(Object obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        CategoryDto dto = (CategoryDto) obj;

        return Category.builder()
                .categoryName(dto.getCategoryName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    @Override
    public List<CategoryDto> toListDto(List list) {
        if (list.isEmpty())
            return Collections.emptyList();

        return list.stream().map(this::toDto).toList();
    }

}

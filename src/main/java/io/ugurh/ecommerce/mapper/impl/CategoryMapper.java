package io.ugurh.ecommerce.mapper.impl;

import io.ugurh.ecommerce.mapper.BaseMapper;
import io.ugurh.ecommerce.model.dto.request.CategoryDto;
import io.ugurh.ecommerce.model.entity.Category;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author harun ugur
 */
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

}

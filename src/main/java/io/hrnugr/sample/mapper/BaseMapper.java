package io.hrnugr.sample.mapper;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BaseMapper {
    CategoryDto toDto(Category model);
    Category toModel(CategoryDto dto);
    List<CategoryDto> toListDto(List<Category> categories);
}

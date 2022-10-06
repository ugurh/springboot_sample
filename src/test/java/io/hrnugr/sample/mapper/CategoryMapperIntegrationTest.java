package io.hrnugr.sample.mapper;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.mapper.impl.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryMapperIntegrationTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void givenModelToDto_whenMaps_thenCorrect() {
        Category model = Category.builder()
                .categoryName("CAT01")
                .description("CAT01 Description")
                .imageUrl("www.image.com/cat01")
                .build();

        CategoryDto dto = categoryMapper.toDto(model);

        assertEquals(model.getCategoryName(), dto.getCategoryName());
        assertEquals(model.getDescription(), dto.getDescription());
    }

    @Test
    void givenDtoToModel_whenMaps_thenCorrect() {
        CategoryDto dto = CategoryDto.builder()
                .categoryName("CAT01")
                .description("CAT01 Description")
                .imageUrl("www.image.com/cat01")
                .build();

        Category model = categoryMapper.toModel(dto);

        assertEquals(dto.getCategoryName(), model.getCategoryName());
        assertEquals(dto.getDescription(), model.getDescription());
    }
}

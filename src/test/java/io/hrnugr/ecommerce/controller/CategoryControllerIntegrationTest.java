package io.hrnugr.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hrnugr.ecommerce.model.dto.request.CategoryDto;
import io.hrnugr.ecommerce.model.entity.Category;
import io.hrnugr.ecommerce.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenCategory_toCreate_thenSuccess() throws Exception {
        CategoryDto dto = CategoryDto.builder()
                .categoryName("CAT")
                .description("Description")
                .imageUrl("www.images.com/01")
                .build();

        mockMvc.perform(post("/category/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        Category category = categoryService.readCategory("CAT");
        assertThat(category.getCategoryName()).isEqualTo("CAT");

    }

}

package io.hrnugr.sample.controllers;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.dto.response.ApiResponseDto;
import io.hrnugr.sample.mapper.impl.CategoryMapper;
import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Operation(summary = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Category already exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class)) }),
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        Category category = categoryMapper.toModel(categoryDto);

        Category categoryInDB = categoryService.readCategory(category.getCategoryName());

        if (Objects.nonNull(categoryInDB)) {
            return new ResponseEntity<>(new ApiResponseDto(false, "category already exists"), HttpStatus.CONFLICT);
        }

        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponseDto(true, "created the category"), HttpStatus.CREATED);
    }
}

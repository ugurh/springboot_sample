package io.hrnugr.sample.controllers;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.dto.response.ApiResponseDto;
import io.hrnugr.sample.entity.Category;
import io.hrnugr.sample.mapper.impl.CategoryMapper;
import io.hrnugr.sample.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author harun ugur
 */
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
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        Category category = categoryMapper.toEntity(categoryDto);

        Category categoryInDb = categoryService.readCategory(category.getCategoryName());

        if (Objects.nonNull(categoryInDb)) {
            return new ResponseEntity<>(new ApiResponseDto(false, "category already exists"), HttpStatus.CONFLICT);
        }

        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponseDto(true, "created the category"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<Category> categories = categoryService.listCategories();

        List<CategoryDto> body = categoryMapper.toListDto(categories);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Operation(summary = "Update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Category doesn't exist",
                    content = @Content)
    })
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable("categoryId") Long categoryId, @Valid @RequestBody CategoryDto categoryDto) {

        if (Objects.isNull(categoryService.get(categoryId))) {
            return new ResponseEntity<>(new ApiResponseDto(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }

        Category category = categoryMapper.toEntity(categoryDto);
        categoryService.update(categoryId, category);

        return new ResponseEntity<>(new ApiResponseDto(true, "updated the category"), HttpStatus.OK);
    }
}

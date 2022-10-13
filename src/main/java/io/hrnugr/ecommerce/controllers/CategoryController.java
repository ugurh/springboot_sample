package io.hrnugr.ecommerce.controllers;

import io.hrnugr.ecommerce.mapper.impl.CategoryMapper;
import io.hrnugr.ecommerce.model.dto.request.CategoryDto;
import io.hrnugr.ecommerce.model.dto.response.ApiResponseDto;
import io.hrnugr.ecommerce.model.entity.Category;
import io.hrnugr.ecommerce.model.response.ECommerceApiResponse;
import io.hrnugr.ecommerce.model.response.ResponseBuilder;
import io.hrnugr.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author harun ugur
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Category already exists",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<ECommerceApiResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        Category category = categoryMapper.toEntity(categoryDto);

        Category categoryInDb = categoryService.readCategory(category.getCategoryName());

        if (Objects.nonNull(categoryInDb)) {
            return new ResponseBuilder().buildResponse(HttpStatus.OK.value(), "category already exists");
        }

        categoryService.createCategory(category);

        log.info("New category created: {} ", category.getCategoryName());
        return new ResponseBuilder().buildResponse(HttpStatus.CREATED.value(), "created the category");
    }

    @GetMapping("/list")
    public ResponseEntity<ECommerceApiResponse> getCategories() {
        List<Category> categories = categoryService.listCategories();

        List<CategoryDto> body = categoryMapper.toListDto(categories);
        return new ResponseBuilder().buildResponse(HttpStatus.OK.value(), "Get all categories", body);
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

package io.ugurh.ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.ugurh.ecommerce.mapper.impl.CategoryMapper;
import io.ugurh.ecommerce.model.dto.request.CategoryDto;
import io.ugurh.ecommerce.model.dto.response.ApiResponseDto;
import io.ugurh.ecommerce.model.entity.Category;
import io.ugurh.ecommerce.model.response.ECommerceApiResponse;
import io.ugurh.ecommerce.model.response.ResponseBuilder;
import io.ugurh.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<Category> categories = categoryService.listCategories();
        categories.forEach(category -> categoryDtos.add(categoryMapper.toDto(category)));
        return new ResponseBuilder().buildResponse(HttpStatus.OK.value(), "Get all categories", categoryDtos);
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

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponseDto> update(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(new ApiResponseDto(true, "deleted the category"), HttpStatus.OK);
    }
}

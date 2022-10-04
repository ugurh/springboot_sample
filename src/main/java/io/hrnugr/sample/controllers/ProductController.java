package io.hrnugr.sample.controllers;

import io.hrnugr.sample.dto.request.CategoryDto;
import io.hrnugr.sample.dto.request.ProductDto;
import io.hrnugr.sample.dto.response.ApiResponseDto;
import io.hrnugr.sample.mapper.impl.ProductMapper;
import io.hrnugr.sample.model.Category;
import io.hrnugr.sample.model.Product;
import io.hrnugr.sample.service.CategoryService;
import io.hrnugr.sample.service.ProductService;
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

import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, CategoryService categoryService, ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Category is invalid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> addProduct(@RequestBody ProductDto productDto) {

        Category category = categoryService.get(productDto.getCategoryId());
        if (Objects.isNull(category)) {
            return new ResponseEntity<>(new ApiResponseDto(false, "Category is invalid"), HttpStatus.CONFLICT);
        }

        Product product = productMapper.toEntity(productDto);
        productService.create(product, category);
        return new ResponseEntity<>(new ApiResponseDto(true, "Product has been added"), HttpStatus.CREATED);
    }
}

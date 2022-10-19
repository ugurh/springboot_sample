package io.ugurh.ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.ugurh.ecommerce.handler.exceptions.CustomException;
import io.ugurh.ecommerce.mapper.impl.ProductMapper;
import io.ugurh.ecommerce.model.dto.request.CategoryDto;
import io.ugurh.ecommerce.model.dto.request.ProductDto;
import io.ugurh.ecommerce.model.dto.response.ApiResponseDto;
import io.ugurh.ecommerce.model.entity.Category;
import io.ugurh.ecommerce.model.entity.Product;
import io.ugurh.ecommerce.service.CategoryService;
import io.ugurh.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author harun ugur
 */
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
    public ResponseEntity<ApiResponseDto> addProduct(@RequestBody @Valid ProductDto productDto) {

        Category category = categoryService.get(productDto.getCategoryId());
        if (Objects.isNull(category)) {
            return new ResponseEntity<>(new ApiResponseDto(false, "Category is invalid"), HttpStatus.CONFLICT);
        }

        Product product = productMapper.toEntity(productDto);
        productService.create(product, category);
        return new ResponseEntity<>(new ApiResponseDto(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> dtoList = new ArrayList<>();
        List<Product> products = productService.listProducts();
        products.forEach(product -> dtoList.add(productMapper.toDto(product)));

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponseDto> updateProduct(@PathVariable("productId") Long productId,
                                                        @RequestBody @Valid ProductDto productDto) throws CustomException {
        Category category = categoryService.get(productDto.getCategoryId());
        if (Objects.isNull(category)) {
            return new ResponseEntity<>(new ApiResponseDto(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        productService.updateProduct(productId, productDto, category);
        return new ResponseEntity<>(new ApiResponseDto(true, "Product has been updated"), HttpStatus.OK);
    }
}

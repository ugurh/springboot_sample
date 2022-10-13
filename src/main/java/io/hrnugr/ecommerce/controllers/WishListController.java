package io.hrnugr.ecommerce.controllers;

import io.hrnugr.ecommerce.handler.exceptions.AuthFailException;
import io.hrnugr.ecommerce.handler.exceptions.CustomException;
import io.hrnugr.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.hrnugr.ecommerce.mapper.impl.ProductMapper;
import io.hrnugr.ecommerce.model.dto.request.ProductDto;
import io.hrnugr.ecommerce.model.dto.request.WishListDto;
import io.hrnugr.ecommerce.model.dto.response.ApiResponseDto;
import io.hrnugr.ecommerce.model.entity.Product;
import io.hrnugr.ecommerce.model.entity.User;
import io.hrnugr.ecommerce.model.entity.WishList;
import io.hrnugr.ecommerce.service.AuthTokenService;
import io.hrnugr.ecommerce.service.ProductService;
import io.hrnugr.ecommerce.service.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author harun ugur
 */
@RestController
@RequestMapping("/wishlist")
public class WishListController {

    private final WishListService wishListService;
    private final AuthTokenService authTokenService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public WishListController(WishListService wishListService, AuthTokenService authTokenService, ProductService productService, ProductMapper productMapper) {
        this.wishListService = wishListService;
        this.authTokenService = authTokenService;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> create(@Valid @RequestBody WishListDto wishListDto, @RequestParam String token) throws CustomException, AuthFailException, ResourceNotFoundException {
        authTokenService.authenticate(token);
        User user = authTokenService.getUser(token);
        Product product = productService.getById(wishListDto.getProductId());

        WishList wishList = new WishList(user, product);
        wishListService.create(wishList);

        return new ResponseEntity<>(new ApiResponseDto(true, "Product has added to wish list."), HttpStatus.CREATED);
    }

    @GetMapping("/list/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) throws CustomException, AuthFailException, ResourceNotFoundException {
        authTokenService.authenticate(token);

        User user = authTokenService.getUser(token);
        List<WishList> wishLists = wishListService.getWishListByUser(user);

        List<ProductDto> products = new ArrayList<>();

        wishLists.forEach(item -> products.add(productMapper.toDto(item.getProduct())));

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}

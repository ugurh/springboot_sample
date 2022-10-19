package io.ugurh.ecommerce.controllers;


import io.ugurh.ecommerce.handler.exceptions.AuthFailException;
import io.ugurh.ecommerce.handler.exceptions.CustomException;
import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.model.dto.request.AddToCartDto;
import io.ugurh.ecommerce.model.dto.response.ApiResponseDto;
import io.ugurh.ecommerce.model.dto.response.CartDto;
import io.ugurh.ecommerce.model.entity.Product;
import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.service.AuthTokenService;
import io.ugurh.ecommerce.service.CartService;
import io.ugurh.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author harun ugur
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final AuthTokenService authTokenService;
    private final ProductService productService;

    public CartController(CartService cartService, AuthTokenService authTokenService, ProductService productService) {
        this.cartService = cartService;
        this.authTokenService = authTokenService;
        this.productService = productService;
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponseDto> addToCart(@RequestBody @Valid AddToCartDto addToCartDto, @RequestParam("token") String token) throws CustomException, AuthFailException, ResourceNotFoundException {
        authTokenService.authenticate(token);

        User user = authTokenService.getUser(token);

        Product product = productService.getById(addToCartDto.getProductId());

        cartService.addToCart(addToCartDto, product, user);

        return new ResponseEntity<>(new ApiResponseDto(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthFailException, CustomException, ResourceNotFoundException {
        authTokenService.authenticate(token);

        User user = authTokenService.getUser(token);

        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponseDto> deleteCartItem(@PathVariable("cartItemId") Long cartItemId,
                                                         @RequestParam("token") String token) throws AuthFailException, CustomException, ResourceNotFoundException {
        authTokenService.authenticate(token);
        User user = authTokenService.getUser(token);
        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<>(new ApiResponseDto(true, "Item has been removed"), HttpStatus.OK);
    }
}

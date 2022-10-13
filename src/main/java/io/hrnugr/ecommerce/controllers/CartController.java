package io.hrnugr.ecommerce.controllers;


import io.hrnugr.ecommerce.handler.exceptions.AuthFailException;
import io.hrnugr.ecommerce.handler.exceptions.CustomException;
import io.hrnugr.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.hrnugr.ecommerce.model.dto.request.AddToCartDto;
import io.hrnugr.ecommerce.model.dto.response.ApiResponseDto;
import io.hrnugr.ecommerce.model.entity.Cart;
import io.hrnugr.ecommerce.model.entity.Product;
import io.hrnugr.ecommerce.model.entity.User;
import io.hrnugr.ecommerce.service.AuthTokenService;
import io.hrnugr.ecommerce.service.CartService;
import io.hrnugr.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<List<Cart>> getCartItems(@RequestParam("token") String token) throws AuthFailException, CustomException, ResourceNotFoundException {
        authTokenService.authenticate(token);

        User user = authTokenService.getUser(token);

        List<Cart> listCartItems = cartService.listCartItems(user);

        return new ResponseEntity<>(listCartItems, HttpStatus.OK);
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

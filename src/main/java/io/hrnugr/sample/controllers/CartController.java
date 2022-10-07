package io.hrnugr.sample.controllers;


import io.hrnugr.sample.dto.request.AddToCartDto;
import io.hrnugr.sample.dto.response.ApiResponseDto;
import io.hrnugr.sample.dto.response.CartDto;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.exceptions.AuthFailException;
import io.hrnugr.sample.exceptions.CartItemNotExistException;
import io.hrnugr.sample.exceptions.CustomException;
import io.hrnugr.sample.exceptions.ResourceNotExistException;
import io.hrnugr.sample.service.AuthTokenService;
import io.hrnugr.sample.service.CartService;
import io.hrnugr.sample.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponseDto> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) throws CustomException, AuthFailException, ResourceNotExistException {
        authTokenService.authenticate(token);

        User user = authTokenService.getUser(token);

        Product product = productService.getById(addToCartDto.getProductId());

        cartService.addToCart(addToCartDto, product, user);

        return new ResponseEntity<>(new ApiResponseDto(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthFailException, CustomException, ResourceNotExistException {
        authTokenService.authenticate(token);

        User user = authTokenService.getUser(token);

        CartDto cartDto = cartService.listCartItems(user);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponseDto> deleteCartItem(@PathVariable("cartItemId") Long cartItemId,
                                                         @RequestParam("token") String token) throws AuthFailException, CustomException, ResourceNotExistException, CartItemNotExistException {
        authTokenService.authenticate(token);
        User user = authTokenService.getUser(token);
        cartService.deleteCartItem(cartItemId, user);
        return new ResponseEntity<>(new ApiResponseDto(true, "Item has been removed"), HttpStatus.OK);
    }
}

package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.dto.request.AddToCartDto;
import io.hrnugr.sample.dto.response.CartDto;
import io.hrnugr.sample.dto.response.CartItemDto;
import io.hrnugr.sample.entity.Cart;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.mapper.impl.ProductMapper;
import io.hrnugr.sample.repository.CartRepository;
import io.hrnugr.sample.service.CartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private final ProductMapper productMapper;

    public CartServiceImp(CartRepository cartRepository, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {

        Cart cart = Cart.builder()
                .product(product)
                .user(user)
                .quantity(addToCartDto.getQuantity())
                .createdDate(LocalDateTime.now())
                .build();

        cartRepository.save(cart);
    }

    @Override
    public CartDto listCartItems(User user) {
        List<Cart> carts = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();

        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Cart cart : carts) {
            CartItemDto cartItemDto = CartItemDto.builder()
                    .productDto(productMapper.toDto(cart.getProduct()))
                    .id(cart.getId())
                    .quantity(cart.getQuantity())
                    .build();

            totalPrice = totalPrice.add(BigDecimal.valueOf(cart.getProduct().getPrice()));
            cartItems.add(cartItemDto);
        }

        return CartDto.builder()
                .cartItems(cartItems)
                .totalPrice(totalPrice)
                .build();
    }
}

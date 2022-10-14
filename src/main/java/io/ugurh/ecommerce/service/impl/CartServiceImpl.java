package io.ugurh.ecommerce.service.impl;

import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.mapper.impl.ProductMapper;
import io.ugurh.ecommerce.model.dto.request.AddToCartDto;
import io.ugurh.ecommerce.model.dto.response.CartItemDto;
import io.ugurh.ecommerce.model.entity.Cart;
import io.ugurh.ecommerce.model.entity.Product;
import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.repository.CartRepository;
import io.ugurh.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author harun ugur
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductMapper productMapper;

    public CartServiceImpl(CartRepository cartRepository, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {

        Cart cart = Cart.builder()
                .product(product)
                .user(user)
                .quantity(addToCartDto.getQuantity())
                .build();

        cartRepository.save(cart);
    }

    @Override
    public List<Cart> listCartItems(User user) {
        List<Cart> carts = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();

        BigDecimal totalPrice = new BigDecimal("0.00");
        for (Cart cart : carts) {
            CartItemDto cartItemDto = CartItemDto.builder()
                    .productDto(productMapper.toDto(cart.getProduct()))
                    .id(cart.getId())
                    .quantity(cart.getQuantity())
                    .build();

            totalPrice = totalPrice.add(cart.getProduct().getPrice());
            cartItems.add(cartItemDto);
        }

        return Collections.emptyList();
    }

    @Override
    public void deleteCartItem(Long cartItemId, User user) {

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new ResourceNotFoundException("Cart does not exist");
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw new ResourceNotFoundException("Cart item does not belong to user");
        }

        cartRepository.deleteById(cartItemId);
    }

}

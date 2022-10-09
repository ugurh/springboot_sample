package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.AddToCartDto;
import io.hrnugr.sample.dto.response.CartDto;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.handler.exceptions.CartItemNotExistException;

/**
 * @author harun ugur
 */
public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);

    CartDto listCartItems(User user);

    void deleteCartItem(Long cartItemId, User user) throws CartItemNotExistException;
}

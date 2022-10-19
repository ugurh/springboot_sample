package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.model.dto.request.AddToCartDto;
import io.ugurh.ecommerce.model.dto.response.CartDto;
import io.ugurh.ecommerce.model.entity.Product;
import io.ugurh.ecommerce.model.entity.User;

/**
 * @author harun ugur
 */
public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);

    CartDto listCartItems(User user);

    void deleteCartItem(Long cartItemId, User user);
}

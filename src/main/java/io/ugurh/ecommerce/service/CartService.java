package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.model.dto.request.AddToCartDto;
import io.ugurh.ecommerce.model.entity.Cart;
import io.ugurh.ecommerce.model.entity.Product;
import io.ugurh.ecommerce.model.entity.User;

import java.util.List;

/**
 * @author harun ugur
 */
public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);

    List<Cart> listCartItems(User user);

    void deleteCartItem(Long cartItemId, User user);
}

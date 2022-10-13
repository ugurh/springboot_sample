package io.hrnugr.ecommerce.service;

import io.hrnugr.ecommerce.model.dto.request.AddToCartDto;
import io.hrnugr.ecommerce.model.entity.Cart;
import io.hrnugr.ecommerce.model.entity.Product;
import io.hrnugr.ecommerce.model.entity.User;

import java.util.List;

/**
 * @author harun ugur
 */
public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);

    List<Cart> listCartItems(User user);

    void deleteCartItem(Long cartItemId, User user);
}

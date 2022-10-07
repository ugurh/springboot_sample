package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.AddToCartDto;
import io.hrnugr.sample.dto.response.CartDto;
import io.hrnugr.sample.entity.Product;
import io.hrnugr.sample.entity.User;

public interface CartService {
    void addToCart(AddToCartDto addToCartDto, Product product, User user);

    CartDto listCartItems(User user);
}

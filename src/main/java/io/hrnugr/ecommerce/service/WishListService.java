package io.hrnugr.ecommerce.service;

import io.hrnugr.ecommerce.model.entity.User;
import io.hrnugr.ecommerce.model.entity.WishList;

import java.util.List;

/**
 * @author harun ugur
 */
public interface WishListService {

    void create(WishList wishList);

    List<WishList> getWishListByUser(User user);

}

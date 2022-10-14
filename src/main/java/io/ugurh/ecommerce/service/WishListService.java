package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.model.entity.WishList;

import java.util.List;

/**
 * @author harun ugur
 */
public interface WishListService {

    void create(WishList wishList);

    List<WishList> getWishListByUser(User user);

}

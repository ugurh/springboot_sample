package io.hrnugr.sample.service;

import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.entity.WishList;

import java.util.List;

public interface WishListService {

    void create(WishList wishList);

    List<WishList> getWishListByUser(User user);

}
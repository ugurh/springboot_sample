package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.entity.WishList;
import io.hrnugr.sample.repository.WishListRepository;
import io.hrnugr.sample.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author harun ugur
 */
@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;

    public WishListServiceImpl(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public void create(WishList wishList) {
        wishListRepository.save(wishList);
    }

    @Override
    public List<WishList> getWishListByUser(User user) {
        return wishListRepository.findWishListByUser(user);
    }
}

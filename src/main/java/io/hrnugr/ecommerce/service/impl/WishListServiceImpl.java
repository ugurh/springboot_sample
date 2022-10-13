package io.hrnugr.ecommerce.service.impl;

import io.hrnugr.ecommerce.model.entity.User;
import io.hrnugr.ecommerce.model.entity.WishList;
import io.hrnugr.ecommerce.repository.WishListRepository;
import io.hrnugr.ecommerce.service.WishListService;
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
        return wishListRepository.findByUser(user);
    }
}

package io.ugurh.ecommerce.service.impl;

import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.model.entity.WishList;
import io.ugurh.ecommerce.repository.WishListRepository;
import io.ugurh.ecommerce.service.WishListService;
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

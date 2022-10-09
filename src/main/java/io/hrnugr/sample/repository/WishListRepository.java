package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author harun ugur
 */
@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    /**
     * fetch the list of wishlist by user
     *
     * @param user user
     * @return List<WishList>
     */
    List<WishList> findWishListByUser(User user);
}

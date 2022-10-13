package io.hrnugr.ecommerce.repository;

import io.hrnugr.ecommerce.model.entity.User;
import io.hrnugr.ecommerce.model.entity.WishList;
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
    List<WishList> findByUser(User user);
}

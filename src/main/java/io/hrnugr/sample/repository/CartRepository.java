package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.Cart;
import io.hrnugr.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author harun ugur
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * fetch all carts by user
     *
     * @param user user
     * @return List<Cart>
     */
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

}

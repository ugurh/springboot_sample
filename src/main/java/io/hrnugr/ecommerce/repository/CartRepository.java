package io.hrnugr.ecommerce.repository;

import io.hrnugr.ecommerce.model.entity.Cart;
import io.hrnugr.ecommerce.model.entity.User;
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

package io.ugurh.ecommerce.repository;

import io.ugurh.ecommerce.model.entity.Cart;
import io.ugurh.ecommerce.model.entity.User;
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

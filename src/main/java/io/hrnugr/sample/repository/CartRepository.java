package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.Cart;
import io.hrnugr.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);

}

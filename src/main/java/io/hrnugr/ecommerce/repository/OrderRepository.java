package io.hrnugr.ecommerce.repository;

import io.hrnugr.ecommerce.model.entity.Order;
import io.hrnugr.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author harun ugur
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    /**
     * fetch all the orders by user order by created date
     *
     * @param user user
     * @return List<Order>
     */
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);

}
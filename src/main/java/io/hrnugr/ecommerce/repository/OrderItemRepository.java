package io.hrnugr.ecommerce.repository;

import io.hrnugr.ecommerce.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author harun ugur
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {
}
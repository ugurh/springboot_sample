package io.hrnugr.ecommerce.repository;

import io.hrnugr.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author harun ugur
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * get count of deleted products
     *
     * @return count
     */
    @Query(value = "SELECT count(u.id) FROM PRO.Product u WHERE u.deleted='true'", nativeQuery = true)
    long countDeletedEntries();
}

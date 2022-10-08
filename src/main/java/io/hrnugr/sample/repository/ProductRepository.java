package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT count(u.id) FROM Product u WHERE u.deleted='true'", nativeQuery = true)
    long countDeletedEntries();
}

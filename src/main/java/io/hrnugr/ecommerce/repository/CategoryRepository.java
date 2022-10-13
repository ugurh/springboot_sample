package io.hrnugr.ecommerce.repository;

import io.hrnugr.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author harun ugur
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * fetch category by category name
     *
     * @param categoryName category name
     * @return Category
     */
    Category getByCategoryName(String categoryName);
}

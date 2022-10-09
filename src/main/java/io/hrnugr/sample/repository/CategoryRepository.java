package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.Category;
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

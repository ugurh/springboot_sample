package io.ugurh.ecommerce.repository;

import io.ugurh.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Modifying
    @Query("update Category c set c.deleted = true where c.id = :id")
    void deleteCategory(@NonNull Long id);
}

package io.hrnugr.sample.repository;

import io.hrnugr.sample.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByCategoryName(String categoryName);
}

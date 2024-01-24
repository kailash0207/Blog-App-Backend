package com.kailash.BlogApp.repositories;

import com.kailash.BlogApp.models.Entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByCategoryName(String categoryName);
}

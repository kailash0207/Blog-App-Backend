package com.kailash.BlogApp.repositories;

import com.kailash.BlogApp.models.Entities.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.USER_ID;


public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
    Page<BlogEntity> findByCreatedBy_UserId(Integer userId, Pageable pageable);
    Page<BlogEntity> findByCategories_CategoryId(Integer categoryId, Pageable pageable);
    Page<BlogEntity> findByBlogTitleIgnoreCaseContainingOrBlogContentIgnoreCaseContaining(String titleKeyword, String contentKeyword, Pageable pageable);

}

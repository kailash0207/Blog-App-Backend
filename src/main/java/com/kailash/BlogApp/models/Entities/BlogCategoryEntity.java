package com.kailash.BlogApp.models.Entities;

import jakarta.persistence.*;
import lombok.*;

import static com.kailash.BlogApp.utils.Constants.ColumnNames.BLOG_ID;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.CATEGORY_ID;
import static com.kailash.BlogApp.utils.Constants.TableNames.BLOG_CATEGORY;

@Entity
@Getter
@Setter
@Table(name = BLOG_CATEGORY)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = BLOG_ID, nullable = false)
    private BlogEntity blog;

    @ManyToOne
    @JoinColumn(name = CATEGORY_ID, nullable = false)
    private CategoryEntity categoryEntity;

}

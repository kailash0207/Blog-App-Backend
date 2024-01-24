package com.kailash.BlogApp.models.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.kailash.BlogApp.utils.Constants.ColumnNames.*;
import static com.kailash.BlogApp.utils.Constants.TableNames.CATEGORY;

@Entity
@Table(name = CATEGORY, uniqueConstraints = @UniqueConstraint(columnNames = {CATEGORY_NAME}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CATEGORY_ID, nullable = false, updatable = false)
    private Integer categoryId;

    @Column(name = CATEGORY_NAME, nullable = false)
    private String categoryName;

    @Column(name = CATEGORY_DESCRIPTION)
    private String categoryDescription;

    @ManyToMany(mappedBy = CATEGORIES, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogEntity> blogs;


}

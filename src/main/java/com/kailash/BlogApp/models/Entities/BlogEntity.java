package com.kailash.BlogApp.models.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

import static com.kailash.BlogApp.utils.Constants.ColumnNames.*;
import static com.kailash.BlogApp.utils.Constants.TableNames.BLOG;
import static com.kailash.BlogApp.utils.Constants.TableNames.BLOG_CATEGORY;

@Entity
@Getter
@Setter
@Table(name = BLOG)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BLOG_ID, nullable = false, updatable = false)
    private Integer blogId;

    @Column(name = BLOG_TITLE, nullable = false, length = 120)
    private String blogTitle;

    @Column(name = BLOG_CONTENT, nullable = false, length = 50000)
    private String blogContent;

    @Column(name = IMAGE_URL)
    private String imageUrl;

    @Column(name = CREATED_ON, nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(name = UPDATED_ON, nullable = false)
    @UpdateTimestamp
    private Timestamp updatedOn;

    @ManyToOne
    @JoinColumn(name = CREATED_BY, nullable = false, updatable = false)
    private UserEntity createdBy;

    @ManyToMany
    @JoinTable(name = BLOG_CATEGORY, joinColumns = @JoinColumn(name = BLOG_ID), inverseJoinColumns = @JoinColumn(name = CATEGORY_ID))
    private List<CategoryEntity> categories;

}

package com.kailash.BlogApp.models.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

import static com.kailash.BlogApp.utils.Constants.TableNames.*;
import static com.kailash.BlogApp.utils.Constants.ColumnNames.*;

@Entity
@Table(name = USER, uniqueConstraints = @UniqueConstraint(columnNames = {EMAIL_ID}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = USER_ID, nullable = false, updatable = false)
    private Integer userId;

    @Column(name = USER_NAME, nullable = false)
    private String userName;

    @Column(name = EMAIL_ID, nullable = false, updatable = false)
    private String emailId;

    @Column(name = PASSWORD, nullable = false)
    private String password;

    @Column(name = ABOUT, length = 500)
    private String about;

    @Column(name = CREATED_ON, nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(name = UPDATED_ON, nullable = false)
    @UpdateTimestamp
    private Timestamp updatedOn;

    @OneToMany(mappedBy = CREATED_BY_CAME_CASE, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogEntity> blogs;

}

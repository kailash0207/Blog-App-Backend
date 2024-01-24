package com.kailash.BlogApp.repositories;

import com.kailash.BlogApp.models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmailId(String emailId);
}

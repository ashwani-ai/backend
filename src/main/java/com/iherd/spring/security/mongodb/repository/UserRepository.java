package com.iherd.spring.security.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iherd.spring.security.mongodb.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findBymobilenumber(String mobilenumber);

    Boolean existsByPassword(String Password);

    Boolean existsBymobilenumber(String mobilenumber);

}
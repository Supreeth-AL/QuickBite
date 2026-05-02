package com.QuickBite.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuickBite.BackEnd.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
}


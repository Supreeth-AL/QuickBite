package com.QuickBite.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuickBite.BackEnd.model.Cart;
import java.util.List;


public interface CartRepository
        extends JpaRepository<Cart, Integer> {

            List<Cart> findByUserId(int userId);
}
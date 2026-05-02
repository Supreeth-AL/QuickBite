package com.QuickBite.BackEnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuickBite.BackEnd.model.Order;

public interface OrderRepository
        extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);
}
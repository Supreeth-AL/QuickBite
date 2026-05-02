package com.QuickBite.BackEnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuickBite.BackEnd.model.Food;

public interface FoodRepository
        extends JpaRepository<Food, Integer> {

}
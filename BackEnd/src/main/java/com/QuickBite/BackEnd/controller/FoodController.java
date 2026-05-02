package com.QuickBite.BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.QuickBite.BackEnd.model.Food;
import com.QuickBite.BackEnd.repository.FoodRepository;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRepository repo;

    @PostMapping("/add")
    public Food addFood(@RequestBody Food food) {

        return repo.save(food);
    }

    @GetMapping("/all")
    public List<Food> getAllFood() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Object getFoodById(@PathVariable int id) {
        Food food = repo.findById(id).orElse(null);

        if (food != null) {
            return food;
        }
        return "Food Not Found";
    }

    @PutMapping("/{id}")
    public Object updateFood(@PathVariable int id, @RequestBody Food updateFood) {

        Food existingFood = repo.findById(id).orElse(null);

        if (existingFood != null) {
            existingFood.setFoodName(updateFood.getFoodName());
            existingFood.setDescription(updateFood.getDescription());
            existingFood.setPrice(updateFood.getPrice());
            existingFood.setCategory(updateFood.getCategory());
            existingFood.setAvailable(updateFood.isAvailable());

            return repo.save(existingFood);
        }

        return "Food Not Found";
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable int id) {
        Food food = repo.findById(id).orElse(null);

        if (food != null) {
            repo.delete(food);
            return "Food Deleted Successfully";
        }

        return "Food not Found";
    }

}
package com.QuickBite.BackEnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.QuickBite.BackEnd.model.Cart;
import com.QuickBite.BackEnd.repository.CartRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository repo;

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {

        return repo.save(cart);
    }

    @GetMapping("/{userId}")
    public List<Cart> getUserCart(@PathVariable int userId) {
        return repo.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public String removeCartItem(@PathVariable int  id){
        Cart cart = repo.findById(id).orElse(null);

        if(cart!=null){
            repo.delete(cart);
            return "cart Item Remove";
        }

        return "Cart item not found";
    }

}
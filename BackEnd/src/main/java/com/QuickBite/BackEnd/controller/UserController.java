package com.QuickBite.BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.QuickBite.BackEnd.model.User;
import com.QuickBite.BackEnd.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return repo.save(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        User existingUser = repo.findByEmailAndPassword(
                user.getEmail(),
                user.getPassword());

        if (existingUser != null) {
            return existingUser;
        }

        return "Invalid Email or password";
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable int id) {

        User user = repo.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        return "User Not Found";
    }

    @PutMapping("/{id}")
    public Object updateUser(
            @PathVariable int id,
            @RequestBody User updatedUser) {

        User existingUser = repo.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRole(updatedUser.getRole());

            return repo.save(existingUser);
        }

        return "User Not Found";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = repo.findById(id).orElse(null);
        if (user != null) {
            repo.delete(user);
            return "User Deleted Successfully";
        }

        return "User Not Found";
    }

}
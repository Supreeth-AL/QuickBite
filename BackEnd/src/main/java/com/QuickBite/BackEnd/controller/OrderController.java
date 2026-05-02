package com.QuickBite.BackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.QuickBite.BackEnd.model.Order;
import com.QuickBite.BackEnd.repository.OrderRepository;

// import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order) {
        order.setOrderStatus("PENDING");
        return repo.save(order);
    }

    @GetMapping("/user/{id}")
    public List<Order> getUserOrderId(@PathVariable int id) {
        return repo.findByUserId(id);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @PutMapping("/{id}")
    public Object updateOrderStatus(
            @PathVariable int id,
            @RequestBody Order updatedOrder) {

        Order existingOrder = repo.findById(id).orElse(null);

        if (existingOrder != null) {

            existingOrder.setOrderStatus(
                    updatedOrder.getOrderStatus());

            return repo.save(existingOrder);
        }

        return "Order Not Found";
    }

}
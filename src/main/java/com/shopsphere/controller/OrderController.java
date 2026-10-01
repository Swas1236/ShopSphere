package com.shopsphere.controller;

import com.shopsphere.model.Order;
import com.shopsphere.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(
            @RequestParam Long userId) {

        return orderService.placeOrder(userId);
    }

    @GetMapping
    public List<Order> getUserOrders(
            @RequestParam Long userId) {

        return orderService.getUserOrders(userId);
    }
}



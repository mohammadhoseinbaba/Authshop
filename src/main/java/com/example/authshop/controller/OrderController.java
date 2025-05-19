package com.example.authshop.controller;

import com.example.authshop.dto.OrderRequest;
import com.example.authshop.model.Order;
import com.example.authshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // USER: Place an order
    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest request, Authentication auth) {
        return orderService.placeOrder(request, auth);
    }

    // USER: Get my orders
    @GetMapping("/my")
    public List<Order> getMyOrders(Authentication auth) {
        return orderService.getUserOrders(auth);
    }

    // ADMIN: Get all orders
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

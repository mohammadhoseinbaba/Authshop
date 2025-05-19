package com.example.authshop.service;

import com.example.authshop.dto.OrderRequest;
import com.example.authshop.model.*;
import com.example.authshop.model.Product;
import com.example.authshop.model.User;
import com.example.authshop.repository.OrderRepository;
import com.example.authshop.repository.ProductRepository;
import com.example.authshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(OrderRequest request, Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found "));

        List<Product> products = productRepository.findAllById(request.getProductIds());
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        Order order = new Order(user, products, total);
        return orderRepository.save(order);
    }
    public List<Order> getUserOrders(Authentication auth){
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}

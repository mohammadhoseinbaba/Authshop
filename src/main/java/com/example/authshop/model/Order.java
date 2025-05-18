package com.example.authshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private double totalPrice;

    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;

    //constructor
    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(User user, List<Product> products, double totalPrice) {
    this.user=user;
    this.products=products;
    this.totalPrice=totalPrice;
    this.orderDate=LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

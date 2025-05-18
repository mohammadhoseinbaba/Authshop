package com.example.authshop.controller;

import com.example.authshop.model.Product;
import com.example.authshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //get all
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    //get one
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    //only admin
    @PreAuthorize("hasAuthority('ROLE_ADMIN)")
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}

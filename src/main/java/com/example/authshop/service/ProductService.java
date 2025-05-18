package com.example.authshop.service;

import com.example.authshop.model.Product;
import com.example.authshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not find by this id:" + id));
    }
    public Product update(Long id , Product updated){
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setPrice(updated.getPrice());
        return productRepository.save(existing);
    }
    public void delete(Long id){
        productRepository.deleteById(id);
    }
}

package com.shopsphere.repository;

import com.shopsphere.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search products by name
    List<Product> findByNameContainingIgnoreCase(String name);

    // Filter products by category
    List<Product> findByCategoryIgnoreCase(String category);

    // Filter products by price range
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}




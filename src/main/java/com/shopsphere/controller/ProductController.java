package com.shopsphere.controller;

import com.shopsphere.model.Product;
import com.shopsphere.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping("/api/products")
    public List<Product> products() {
        return productService.getProducts();
    }

    // Get product by ID
    @GetMapping("/api/products/{id}")
    public Product productById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Search products by name
    @GetMapping("/api/products/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProducts(name);
    }

    // Filter products by category
    @GetMapping("/api/products/category")
    public List<Product> productsByCategory(
            @RequestParam String category) {

        return productService.getProductsByCategory(category);
    }

    // Filter products by price range
    @GetMapping("/api/products/price")
    public List<Product> productsByPrice(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return productService.getProductsByPriceRange(
                minPrice,
                maxPrice
        );
    }

    // Create new product
    @PostMapping("/api/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Update product
    @PutMapping("/api/products/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    // Delete product
    @DeleteMapping("/api/products/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }
}


package com.shopsphere.service;

import com.shopsphere.model.Product;
import com.shopsphere.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable("products")
    public List<Product> getProducts() {

        System.out.println("Fetching products from MySQL...");

        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository
                .findById(id)
                .orElse(null);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(
            Long id,
            Product updatedProduct) {

        Product product =
                productRepository
                        .findById(id)
                        .orElse(null);

        if (product != null) {

            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setBrand(updatedProduct.getBrand());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            product.setDescription(updatedProduct.getDescription());
            product.setRating(updatedProduct.getRating());
            product.setImageUrl(updatedProduct.getImageUrl());
            product.setActive(updatedProduct.isActive());

            return productRepository.save(product);
        }

        return null;
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String name) {

        return productRepository
                .findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(
            String category) {

        return productRepository
                .findByCategoryIgnoreCase(category);
    }

    public List<Product> getProductsByPriceRange(
            double minPrice,
            double maxPrice) {

        return productRepository
                .findByPriceBetween(
                        minPrice,
                        maxPrice
                );
    }
}


package com.shopsphere.controller;

import com.shopsphere.model.CartItem;
import com.shopsphere.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartItem addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return cartService.addToCart(
                userId,
                productId,
                quantity
        );
    }

    @GetMapping
    public List<CartItem> getCart(
            @RequestParam Long userId) {

        return cartService.getCartItems(userId);
    }

    @PutMapping("/update")
    public CartItem updateCartItem(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        return cartService.updateCartItem(
                userId,
                productId,
                quantity
        );
    }

    @DeleteMapping("/remove")
    public String removeCartItem(
            @RequestParam Long userId,
            @RequestParam Long productId) {

        cartService.removeCartItem(
                userId,
                productId
        );

        return "Product removed from cart";
    }
}


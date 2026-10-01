package com.shopsphere.service;

import com.shopsphere.model.Cart;
import com.shopsphere.model.CartItem;
import com.shopsphere.repository.CartItemRepository;
import com.shopsphere.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart getOrCreateCart(Long userId) {

        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {

                    Cart cart = new Cart();
                    cart.setUserId(userId);

                    return cartRepository.save(cart);
                });
    }

    public CartItem addToCart(
            Long userId,
            Long productId,
            int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than 0"
            );
        }

        Cart cart = getOrCreateCart(userId);

        CartItem cartItem =
                cartItemRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                productId
                        )
                        .orElse(null);

        if (cartItem == null) {

            cartItem = new CartItem();

            cartItem.setCartId(cart.getId());
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);

        } else {

            cartItem.setQuantity(
                    cartItem.getQuantity() + quantity
            );
        }

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(Long userId) {

        Cart cart = getOrCreateCart(userId);

        return cartItemRepository.findByCartId(
                cart.getId()
        );
    }

    public CartItem updateCartItem(
            Long userId,
            Long productId,
            int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than 0"
            );
        }

        Cart cart = cartRepository
                .findByUserId(userId)
                .orElse(null);

        if (cart == null) {
            return null;
        }

        CartItem cartItem =
                cartItemRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                productId
                        )
                        .orElse(null);

        if (cartItem == null) {
            return null;
        }

        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public void removeCartItem(
            Long userId,
            Long productId) {

        Cart cart = cartRepository
                .findByUserId(userId)
                .orElse(null);

        if (cart == null) {
            return;
        }

        CartItem cartItem =
                cartItemRepository
                        .findByCartIdAndProductId(
                                cart.getId(),
                                productId
                        )
                        .orElse(null);

        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
        }
    }
}


package com.shopsphere.service;

import com.shopsphere.exception.InsufficientStockException;
import com.shopsphere.model.Cart;
import com.shopsphere.model.CartItem;
import com.shopsphere.model.Order;
import com.shopsphere.model.OrderItem;
import com.shopsphere.model.Product;
import com.shopsphere.repository.CartItemRepository;
import com.shopsphere.repository.CartRepository;
import com.shopsphere.repository.OrderItemRepository;
import com.shopsphere.repository.OrderRepository;
import com.shopsphere.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order placeOrder(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(null);

        if (cart == null) {
            return null;
        }

        List<CartItem> cartItems =
                cartItemRepository.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {
            return null;
        }

        double totalAmount = 0;

        // Check stock and calculate total
        for (CartItem cartItem : cartItems) {

            Product product =
                    productRepository
                            .findById(cartItem.getProductId())
                            .orElse(null);

            if (product == null) {
                return null;
            }

            if (product.getStock() < cartItem.getQuantity()) {

                throw new InsufficientStockException(
                        "Insufficient stock for product: "
                                + product.getName()
                );
            }

            totalAmount +=
                    product.getPrice()
                            * cartItem.getQuantity();
        }

        // Create order
        Order order = new Order();

        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");

        Order savedOrder =
                orderRepository.save(order);

        // Create order items and reduce stock
        for (CartItem cartItem : cartItems) {

            Product product =
                    productRepository
                            .findById(cartItem.getProductId())
                            .orElse(null);

            if (product != null) {

                OrderItem orderItem =
                        new OrderItem();

                orderItem.setOrderId(savedOrder.getId());
                orderItem.setProductId(product.getId());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(product.getPrice());

                orderItemRepository.save(orderItem);

                // Reduce stock
                product.setStock(
                        product.getStock()
                                - cartItem.getQuantity()
                );

                productRepository.save(product);
            }
        }

        // Clear cart
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    public List<Order> getUserOrders(Long userId) {

        return orderRepository.findByUserId(userId);
    }
}


package com.example.fooddelivery_app.utils;

import com.example.fooddelivery_app.models.CartItem;
import java.util.*;

public class CartManager {
    private static CartManager instance;
    private Map<String, List<CartItem>> userCarts = new HashMap<>();
    private String currentUserId;

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) instance = new CartManager();
        return instance;
    }

    public void setCurrentUser(String userId) {
        currentUserId = userId;
        userCarts.putIfAbsent(userId, new ArrayList<>());
    }

    public void addToCart(CartItem item) {
        if (currentUserId == null) return;
        List<CartItem> cart = userCarts.get(currentUserId);
        for (CartItem c : cart) {
            if (c.getTitle().equals(item.getTitle())) {
                c.setQuantity(c.getQuantity() + item.getQuantity());
                return;
            }
        }
        cart.add(item);
    }

    public void removeFromCart(CartItem item) {
        if (currentUserId == null) return;
        userCarts.get(currentUserId).remove(item);
    }

    public List<CartItem> getCartItems() {
        if (currentUserId == null) return new ArrayList<>();
        return userCarts.get(currentUserId);
    }

    public double getSubtotal() {
        double sum = 0;
        if (currentUserId == null) return sum;
        for (CartItem item : userCarts.get(currentUserId)) sum += item.getTotalPrice();
        return sum;
    }

    public void clearCart() {
        if (currentUserId == null) return;
        userCarts.get(currentUserId).clear();
    }
}
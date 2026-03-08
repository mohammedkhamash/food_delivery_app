package com.example.fooddelivery_app.utils;

import com.example.fooddelivery_app.models.Order;
import java.util.*;

public class OrderManager {
    private static OrderManager instance;

    private Map<String, List<Order>> userOrders = new HashMap<>();
    private String currentUserId;

    private OrderManager() {}

    public static OrderManager getInstance() {
        if (instance == null) instance = new OrderManager();
        return instance;
    }

    // تعيين المستخدم الحالي
    public void setCurrentUser(String userId) {
        currentUserId = userId;
        userOrders.putIfAbsent(userId, new ArrayList<>());
    }

    public void addOrder(Order order) {
        if (currentUserId == null) return;
        userOrders.get(currentUserId).add(order);
    }

    public List<Order> getOrders() {
        if (currentUserId == null) return new ArrayList<>();
        return userOrders.get(currentUserId);
    }
}
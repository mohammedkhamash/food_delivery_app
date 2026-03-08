package com.example.fooddelivery_app.models;

import java.util.List;

public class Order {
    private String date;
    private List<CartItem> items;
    private double total;

    public Order(String date, List<CartItem> items, double total) {
        this.date = date;
        this.items = items;
        this.total = total;
    }

    public String getDate() { return date; }
    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
}
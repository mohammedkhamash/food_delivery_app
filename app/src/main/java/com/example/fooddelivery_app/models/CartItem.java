package com.example.fooddelivery_app.models;

public class CartItem {
    private String title;
    private double price;
    private int quantity;
    private int imageResId;

    public CartItem(String title, double price, int quantity, int imageResId) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getImageResId() { return imageResId; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return price * quantity; }
}
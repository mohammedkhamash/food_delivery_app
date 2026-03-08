package com.example.fooddelivery_app.models;

public class Product {

    private String title;
    private int imageResId;
    private double price;
    private String description;
    private double rating;
    private int time; // دقيقة التحضير
    private String category; // اسم الفئة

    public Product(String title, int imageResId, double price, String description,
                   double rating, int time, String category) {
        this.title = title;
        this.imageResId = imageResId;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.time = time;
        this.category = category;
    }

    public String getTitle() { return title; }
    public int getImageResId() { return imageResId; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public double getRating() { return rating; }
    public int getTime() { return time; }
    public String getCategory() { return category; }

    // ===== تعريف equals و hashCode لضمان المفضلة تعمل =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product other = (Product) obj;
        return title.equals(other.title); // العنوان مفتاح فريد
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
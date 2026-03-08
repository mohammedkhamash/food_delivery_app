package com.example.fooddelivery_app.utils;

import com.example.fooddelivery_app.models.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteManager {

    private static FavoriteManager instance;

    // تخزين المفضلة لكل مستخدم
    private Map<String, List<Product>> userFavorites = new HashMap<>();

    // المستخدم الحالي
    private String currentUserId;

    private FavoriteManager() {}

    public static FavoriteManager getInstance() {
        if (instance == null) instance = new FavoriteManager();
        return instance;
    }

    // تحديد المستخدم الحالي
    public void setCurrentUser(String userId) {
        currentUserId = userId;
        userFavorites.putIfAbsent(userId, new ArrayList<>());
    }

    public void addFavorite(Product product) {
        if (currentUserId == null) return;

        List<Product> favoriteList = userFavorites.get(currentUserId);

        if (!favoriteList.contains(product)) {
            favoriteList.add(product);
        }
    }

    public void removeFavorite(Product product) {
        if (currentUserId == null) return;

        userFavorites.get(currentUserId).remove(product);
    }

    public List<Product> getFavorites() {
        if (currentUserId == null) return new ArrayList<>();

        return userFavorites.get(currentUserId);
    }

    public boolean isFavorite(Product product) {
        if (currentUserId == null) return false;

        return userFavorites.get(currentUserId).contains(product);
    }
}
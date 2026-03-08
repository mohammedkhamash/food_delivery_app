package com.example.fooddelivery_app.utils;

import com.example.fooddelivery_app.models.User;
import java.util.ArrayList;
import java.util.List;

public class AuthManager {
    private static AuthManager instance;
    private List<User> users = new ArrayList<>();
    private User currentUser = null;

    private AuthManager() {}

    public static AuthManager getInstance() {
        if (instance == null) instance = new AuthManager();
        return instance;
    }

    public boolean register(User user) {
        for (User u : users) if (u.getEmail().equals(user.getEmail())) return false;
        users.add(user);
        return true;
    }

    public boolean login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    public void logout() { currentUser = null; }
    public User getCurrentUser() { return currentUser; }
}
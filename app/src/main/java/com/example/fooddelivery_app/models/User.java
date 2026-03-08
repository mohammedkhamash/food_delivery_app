package com.example.fooddelivery_app.models;

import java.util.UUID;

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private String address;

    public User(String email, String password, String name, String address) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public String getId() { return id; }       // مهم لربط السلة
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getAddress() { return address; }
}
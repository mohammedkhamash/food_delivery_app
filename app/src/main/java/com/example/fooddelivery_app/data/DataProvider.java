package com.example.fooddelivery_app.data;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.Category;
import com.example.fooddelivery_app.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Product> getBestFoods() {
        List<Product> list = new ArrayList<>();

        // Burger
        list.add(new Product("Beef Burger", R.drawable.b1, 8.99,
                "Juicy beef burger with lettuce, tomato, and cheese.",
                4.5, 15, "Burger"));
        list.add(new Product("Cheese Burger", R.drawable.b2, 9.50,
                "Cheesy delight with a crispy beef patty and fresh toppings.",
                4.2, 18, "Burger"));
        list.add(new Product("Spicy Burger", R.drawable.b3, 8.75,
                "Crispy chicken fillet with spicy mayo, fresh tomato, and lettuce.",
                4.4, 16, "Burger"));
        list.add(new Product("Veggie Burger", R.drawable.b4, 7.90,
                "Healthy veggie patty with avocado, tomato, and lettuce on a soft bun.",
                4.3, 14, "Burger"));
        list.add(new Product("Deluxe Burger", R.drawable.b5, 9.99,
                "Juicy beef burger with cheese, fresh lettuce, and tomato.",
                4.5, 16, "Burger"));

        // Hot Dog
        list.add(new Product("Classic Dog", R.drawable.h1, 5.99,
                "Grilled hot dog with mustard and ketchup.",
                4.0, 10, "Hot Dog"));
        list.add(new Product("Spicy Dog", R.drawable.h2, 6.50,
                "Hot dog with spicy sauce and fresh veggies.",
                4.3, 12, "Hot Dog"));
        list.add(new Product("Cheese Dog", R.drawable.h4, 6.50,
                "Hot dog topped with melted cheese.",
                4.2, 10, "Hot Dog"));
        list.add(new Product("Loaded Dog", R.drawable.h5, 7.00,
                "Hot dogs with assorted toppings and sauces.",
                4.3, 11, "Hot Dog"));

        // Chicken
        list.add(new Product("Chicken Wings", R.drawable.c1, 8.50,
                "Grilled chicken wings or Buffalo wings, perfectly spiced.",
                4.6, 14, "Chicken"));
        list.add(new Product("Spicy Chicken", R.drawable.c2, 9.25,
                "Marinated grilled chicken with hot and tangy sauce.",
                4.4, 12, "Chicken"));

        // Meat
        list.add(new Product("Meat Platter", R.drawable.m1, 13.50,
                "Tender grilled steak with special seasoning.",
                4.7, 25, "Meat"));
        list.add(new Product("BBQ Ribs", R.drawable.m1, 14.00,
                "Juicy BBQ ribs with rich sauce.",
                4.6, 22, "Meat"));

        // Pizza
        list.add(new Product("Margherita", R.drawable.margherita, 10.75,
                "Classic pizza with tomato, mozzarella, and basil.",
                4.5, 20, "Pizza"));
        list.add(new Product("Pepperoni", R.drawable.margherita, 11.50,
                "Pizza topped with pepperoni and melted cheese.",
                4.6, 22, "Pizza"));
        list.add(new Product("Veggie Pizza", R.drawable.p2, 10.50,
                "Vegetable pizza topped with melted cheese.",
                4.5, 18, "Pizza"));
        list.add(new Product("Beef Pizza", R.drawable.p5, 12.25,
                "Pizza with beef, green peppers, and cheese.",
                4.6, 20, "Pizza"));
        list.add(new Product("Italian Pizza", R.drawable.p7, 13.00,
                "Classic Italian pizza with meat chunks and olives.",
                4.7, 22, "Pizza"));

        // Salad
        list.add(new Product("Caesar Salad", R.drawable.s1, 7.25,
                "Fresh lettuce, parmesan, and croutons with Caesar dressing.",
                4.4, 12, "Salad"));
        list.add(new Product("Greek Salad", R.drawable.s1, 7.50,
                "Salad with cucumber, tomato, olives, and feta cheese.",
                4.3, 14, "Salad"));

        // Sushi
        list.add(new Product("Sushi Platter", R.drawable.so1, 15.00,
                "Assorted sushi with fresh fish and rice.",
                4.7, 25, "Sushi"));
        list.add(new Product("Salmon Sushi", R.drawable.so1, 14.50,
                "Delicious salmon sushi rolls.",
                4.6, 20, "Sushi"));

        // Drinks
        list.add(new Product("Orange Juice", R.drawable.j1, 3.50,
                "Freshly squeezed orange juice.",
                4.2, 5, "Drink"));
        list.add(new Product("Green Tea", R.drawable.j4, 3.50,
                "A cup of green tea, served hot.",
                4.4, 8, "Drink"));
        list.add(new Product("Berry Smoothie", R.drawable.j6, 4.50,
                "Strawberry and berry smoothie garnished with mint.",
                4.5, 9, "Drink"));
        list.add(new Product("Coconut Milk", R.drawable.j7, 4.75,
                "Coconut milk served with a fresh coconut fruit.",
                4.6, 7, "Drink"));

        return list;
    }

    public static List<Category> getCategories() {
        List<Category> list = new ArrayList<>();

        list.add(new Category("Pizza", R.drawable.btn_1));
        list.add(new Category("Burger", R.drawable.btn_2));
        list.add(new Category("Chicken", R.drawable.btn_3));
        list.add(new Category("Hot Dog", R.drawable.btn_6));
        list.add(new Category("Meat", R.drawable.btn_5));
        list.add(new Category("Salad", R.drawable.btn_8));
        list.add(new Category("Sushi", R.drawable.btn_4));
        list.add(new Category("Drink", R.drawable.btn_7));

        return list;
    }
}
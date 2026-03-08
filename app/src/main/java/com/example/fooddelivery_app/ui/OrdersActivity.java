package com.example.fooddelivery_app.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapters.OrderAdapter;
import com.example.fooddelivery_app.models.Order;
import com.example.fooddelivery_app.utils.OrderManager;

import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView rvOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        rvOrders = findViewById(R.id.rvOrders);

        List<Order> orders = OrderManager.getInstance().getOrders();
        OrderAdapter adapter = new OrderAdapter(this, orders);

        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        rvOrders.setAdapter(adapter);
    }
}
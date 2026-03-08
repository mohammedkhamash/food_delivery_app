package com.example.fooddelivery_app.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapters.CartAdapter;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.models.Order;
import com.example.fooddelivery_app.utils.CartManager;
import com.example.fooddelivery_app.utils.OrderManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rvCart;
    private TextView tvSubtotal, tvDeliveryFee, tvTotal;
    private Button btnPlaceOrder;

    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;

    private final double DELIVERY_FEE = 2.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCart = findViewById(R.id.rvCart);
        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvDeliveryFee = findViewById(R.id.tvDeliveryFee);
        tvTotal = findViewById(R.id.tvTotal);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        cartItems = CartManager.getInstance().getCartItems();
        cartAdapter = new CartAdapter(this, cartItems, this::updateSummary);

        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.setAdapter(cartAdapter);


        tvDeliveryFee.setText("$" + String.format("%.2f", DELIVERY_FEE));

        updateSummary();

        btnPlaceOrder.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                AlertDialog.Builder emptyDialog = new AlertDialog.Builder(this);
                emptyDialog.setTitle("Cart is empty")
                        .setMessage("Please add items to your cart before placing an order.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }


            double subtotal = CartManager.getInstance().getSubtotal();
            double total = subtotal + DELIVERY_FEE;

            String date = DateFormat.getDateTimeInstance().format(new Date());
            Order order = new Order(date, new ArrayList<>(cartItems), total);

            AlertDialog.Builder confirmDialog = new AlertDialog.Builder(this);
            confirmDialog.setTitle("Confirm Order")
                    .setMessage("Do you want to place this order?\nSubtotal: $" + String.format("%.2f", subtotal) +
                            "\nDelivery Fee: $" + String.format("%.2f", DELIVERY_FEE) +
                            "\nTotal: $" + String.format("%.2f", total))
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // إضافة الطلب
                        OrderManager.getInstance().addOrder(order);

                        // مسح السلة وتحديث الواجهة
                        CartManager.getInstance().clearCart();
                        cartAdapter.notifyDataSetChanged();
                        updateSummary();

                        startActivity(new Intent(CartActivity.this, OrdersActivity.class));
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private void updateSummary() {
        double subtotal = CartManager.getInstance().getSubtotal();
        double total = subtotal + DELIVERY_FEE;

        tvSubtotal.setText("$" + String.format("%.2f", subtotal));
        tvTotal.setText("$" + String.format("%.2f", total));
        tvDeliveryFee.setText("$" + String.format("%.2f", DELIVERY_FEE));
    }
}
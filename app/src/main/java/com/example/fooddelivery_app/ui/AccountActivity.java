package com.example.fooddelivery_app.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.User;
import com.example.fooddelivery_app.utils.AuthManager;

public class AccountActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);

        User user = AuthManager.getInstance().getCurrentUser();

        if (user != null) {
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            tvAddress.setText(user.getAddress());
        }
    }
}
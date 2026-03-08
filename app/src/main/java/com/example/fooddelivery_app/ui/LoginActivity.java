package com.example.fooddelivery_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.utils.AuthManager;
import com.example.fooddelivery_app.utils.CartManager;
import com.example.fooddelivery_app.models.User;
import com.example.fooddelivery_app.utils.FavoriteManager;
import com.example.fooddelivery_app.utils.OrderManager;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "extra_email";

    private EditText etEmail, etPassword;
    private Button btnLoginAction;
    private TextView tvGoToSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLoginAction = findViewById(R.id.btnLoginAction);
        tvGoToSignup = findViewById(R.id.tvGoToSignup);

        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        if (email != null) {
            etEmail.setText(email);
        }

        btnLoginAction.setOnClickListener(v -> {
            String userEmail = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (userEmail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (AuthManager.getInstance().login(userEmail, password)) {


                User currentUser = AuthManager.getInstance().getCurrentUser();
                CartManager.getInstance().setCurrentUser(currentUser.getEmail());
                FavoriteManager.getInstance().setCurrentUser(currentUser.getEmail());
                OrderManager.getInstance().setCurrentUser(currentUser.getEmail());



                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoToSignup.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class))
        );
    }
}
package com.example.fooddelivery_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.User;
import com.example.fooddelivery_app.utils.AuthManager;

public class SignUpActivity extends AppCompatActivity {

    private EditText etNewEmail, etNewPassword, etName, etAddress;
    private Button btnSignupAction;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNewEmail = findViewById(R.id.etNewEmail);
        etNewPassword = findViewById(R.id.etNewPassword);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        btnSignupAction = findViewById(R.id.btnSignupAction);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnSignupAction.setOnClickListener(v -> {
            String email = etNewEmail.getText().toString().trim();
            String password = etNewPassword.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!email.contains("@")) {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                return;
            }

            if(password.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            User newUser = new User(email, password, name, address);
            boolean success = AuthManager.getInstance().register(newUser);

            if(success){
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.putExtra(LoginActivity.EXTRA_EMAIL, email);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            }
        });

        tvBackToLogin.setOnClickListener(v -> finish());
    }
}
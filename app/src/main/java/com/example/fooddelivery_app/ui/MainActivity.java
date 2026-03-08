package com.example.fooddelivery_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapters.CategoryAdapter;
import com.example.fooddelivery_app.adapters.ProductAdapter;
import com.example.fooddelivery_app.data.DataProvider;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.models.Category;
import com.example.fooddelivery_app.models.Product;
import com.example.fooddelivery_app.utils.AuthManager;
import com.example.fooddelivery_app.utils.CartManager;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView ivMenu, ivSearch, ivCart;
    private EditText etSearch;
    private TextView tvWelcome;
    private RecyclerView rvBestFoods, rvCategories;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        ivMenu = findViewById(R.id.ivMenu);
        ivSearch = findViewById(R.id.ivSearch);
        ivCart = findViewById(R.id.ivCart);
        etSearch = findViewById(R.id.etSearch);
        tvWelcome = findViewById(R.id.tvWelcome);
        rvBestFoods = findViewById(R.id.rvBestFoods);
        rvCategories = findViewById(R.id.rvCategories);
        navView = findViewById(R.id.navView);

        String userId = AuthManager.getInstance().getCurrentUser().getId();
        CartManager.getInstance().setCurrentUser(userId);

        tvWelcome.setText("Welcome, " + AuthManager.getInstance().getCurrentUser().getName());

        // ===== RecyclerView للمنتجات =====
        List<Product> products = DataProvider.getBestFoods();
        ProductAdapter productAdapter = new ProductAdapter(this, products, product -> {
            CartItem item = new CartItem(product.getTitle(), product.getPrice(), 1, product.getImageResId());
            CartManager.getInstance().addToCart(item);
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });
        rvBestFoods.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvBestFoods.setAdapter(productAdapter);

        // ===== RecyclerView للفئات =====
        List<Category> categories = DataProvider.getCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        rvCategories.setLayoutManager(new GridLayoutManager(this, 3));
        rvCategories.setAdapter(categoryAdapter);

        categoryAdapter.setOnCategoryClickListener(category -> {
            Intent intent = new Intent(this, CategoryProductsActivity.class);
            intent.putExtra(CategoryProductsActivity.EXTRA_CATEGORY_NAME, category.getName());
            startActivity(intent);
        });

        // ===== البحث =====
        ivSearch.setOnClickListener(v -> performSearch());
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                performSearch();
                return true;
            }
            return false;
        });

        // ===== السلة =====
        ivCart.setOnClickListener(v -> {
            if (AuthManager.getInstance().getCurrentUser() == null) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        // ===== قائمة Navigation =====
        ivMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_account) {
                startActivity(new Intent(this, AccountActivity.class));
            } else if (id == R.id.nav_orders) {
                if (AuthManager.getInstance().getCurrentUser() == null) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    startActivity(new Intent(this, OrdersActivity.class));
                }
            } else if (id == R.id.nav_favorites) {
                startActivity(new Intent(this, FavoritesActivity.class));
            } else if (id == R.id.nav_logout) {
                AuthManager.getInstance().logout();
                CartManager.getInstance().setCurrentUser(null);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void performSearch() {
        String query = etSearch.getText().toString().trim();
        if (!query.isEmpty()) {
            Intent intent = new Intent(this, SearchResultsActivity.class);
            intent.putExtra(SearchResultsActivity.EXTRA_SEARCH_QUERY, query);
            startActivity(intent);
        }
    }
}
package com.example.fooddelivery_app.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapters.ProductAdapter;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.models.Product;
import com.example.fooddelivery_app.utils.CartManager;
import com.example.fooddelivery_app.data.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductsActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_NAME = "category_name";

    private RecyclerView rvProducts;
    private TextView tvCategoryTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        rvProducts = findViewById(R.id.rvProducts);
        tvCategoryTitle = findViewById(R.id.tvCategoryTitle);
        btnBack = findViewById(R.id.btnBack);

        String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        tvCategoryTitle.setText(categoryName);

        btnBack.setOnClickListener(v -> finish());

        List<Product> allProducts = DataProvider.getBestFoods();
        List<Product> filteredProducts = new ArrayList<>();
        for(Product p : allProducts){
            if(p.getCategory().equals(categoryName)){
                filteredProducts.add(p);
            }
        }

        ProductAdapter adapter = new ProductAdapter(this, filteredProducts, product -> {
            CartManager.getInstance().addToCart(
                    new CartItem(product.getTitle(), product.getPrice(), 1, product.getImageResId())
            );
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        });

        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvProducts.setAdapter(adapter);
    }
}
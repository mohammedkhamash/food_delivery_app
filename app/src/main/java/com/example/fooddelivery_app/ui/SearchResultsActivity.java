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
import com.example.fooddelivery_app.data.DataProvider;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.models.Product;
import com.example.fooddelivery_app.utils.CartManager;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    public static final String EXTRA_SEARCH_QUERY = "search_query";

    private ImageView btnBack;
    private TextView tvSearchQuery, tvNoResults;
    private RecyclerView rvSearchResults;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        btnBack = findViewById(R.id.btnBack);
        tvSearchQuery = findViewById(R.id.tvSearchQuery);
        tvNoResults = findViewById(R.id.tvNoResults);
        rvSearchResults = findViewById(R.id.rvSearchResults);

        btnBack.setOnClickListener(v -> finish());


        String query = getIntent().getStringExtra(EXTRA_SEARCH_QUERY);
        tvSearchQuery.setText(query);


        List<Product> allProducts = DataProvider.getBestFoods();
        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(p);
            }
        }


        if (filtered.isEmpty()) {
            tvNoResults.setVisibility(TextView.VISIBLE);
            rvSearchResults.setVisibility(RecyclerView.GONE);
        } else {
            tvNoResults.setVisibility(TextView.GONE);
            rvSearchResults.setVisibility(RecyclerView.VISIBLE);


            adapter = new ProductAdapter(this, filtered, product -> {

                CartItem cartItem = new CartItem(
                        product.getTitle(),
                        product.getPrice(),
                        1,
                        product.getImageResId()
                );
                CartManager.getInstance().addToCart(cartItem);
                Toast.makeText(SearchResultsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
            });

            rvSearchResults.setLayoutManager(new GridLayoutManager(this, 2));
            rvSearchResults.setAdapter(adapter);
        }
    }
}
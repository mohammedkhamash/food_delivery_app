package com.example.fooddelivery_app.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapters.FavoriteAdapter;
import com.example.fooddelivery_app.models.Product;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.utils.CartManager;
import com.example.fooddelivery_app.utils.FavoriteManager;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView rvFavorites;
    private FavoriteAdapter favoriteAdapter;
    private List<Product> favoriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rvFavorites = findViewById(R.id.rvFavorites);

        favoriteList = FavoriteManager.getInstance().getFavorites();

        favoriteAdapter = new FavoriteAdapter(this, favoriteList, product -> {
            CartManager.getInstance().addToCart(new CartItem(
                    product.getTitle(),
                    product.getPrice(),
                    1,
                    product.getImageResId()
            ));
            Toast.makeText(FavoritesActivity.this,
                    product.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        rvFavorites.setLayoutManager(new GridLayoutManager(this, 2));
        rvFavorites.setAdapter(favoriteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        favoriteAdapter.notifyDataSetChanged();
    }
}
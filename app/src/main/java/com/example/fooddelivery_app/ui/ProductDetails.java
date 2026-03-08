package com.example.fooddelivery_app.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.Product;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.utils.CartManager;
import com.example.fooddelivery_app.utils.FavoriteManager;

public class ProductDetails extends AppCompatActivity {

    public static final String EXTRA_PRODUCT_TITLE = "product_title";
    public static final String EXTRA_PRODUCT_PRICE = "product_price";
    public static final String EXTRA_PRODUCT_IMAGE = "product_image";
    public static final String EXTRA_PRODUCT_DESC = "product_desc";
    public static final String EXTRA_PRODUCT_RATING = "product_rating";
    public static final String EXTRA_PRODUCT_TIME = "product_time";

    private ImageView imgProduct, btnBack, btnFav, btnMinus, btnPlus;
    private TextView tvTitle, tvPrice, tvDescription, tvRating, tvTime, tvQuantity, tvTotalPrice;
    private RatingBar ratingBar;
    private Button btnAddToCart;

    private int quantity = 1;
    private double productPrice = 0;
    private Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imgProduct = findViewById(R.id.imgProduct);
        btnBack = findViewById(R.id.btnBack);
        btnFav = findViewById(R.id.btnFav);
        tvTitle = findViewById(R.id.tvTitle);
        tvPrice = findViewById(R.id.tvPrice);
        tvDescription = findViewById(R.id.tvDescription);
        ratingBar = findViewById(R.id.ratingBar);
        tvRating = findViewById(R.id.tvRating);
        tvTime = findViewById(R.id.tvTime);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        String title = getIntent().getStringExtra(EXTRA_PRODUCT_TITLE);
        productPrice = getIntent().getDoubleExtra(EXTRA_PRODUCT_PRICE, 0);
        int imageRes = getIntent().getIntExtra(EXTRA_PRODUCT_IMAGE, R.drawable.margherita);
        String description = getIntent().getStringExtra(EXTRA_PRODUCT_DESC);
        double rating = getIntent().getDoubleExtra(EXTRA_PRODUCT_RATING, 0);
        int time = getIntent().getIntExtra(EXTRA_PRODUCT_TIME, 0);

        currentProduct = new Product(title, imageRes, productPrice, description, rating, time, "");

        tvTitle.setText(title);
        tvPrice.setText("$" + productPrice);
        tvDescription.setText(description != null ? description : "Delicious food!");
        imgProduct.setImageResource(imageRes);
        ratingBar.setRating((float) rating);
        tvRating.setText(rating + " Rating");
        tvTime.setText(time + " min");


        tvQuantity.setText(String.valueOf(quantity));
        updateTotalPrice();

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
                updateTotalPrice();
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
            updateTotalPrice();
        });


        btnBack.setOnClickListener(v -> finish());


        btnAddToCart.setOnClickListener(v -> {
            CartItem cartItem = new CartItem(
                    title,
                    productPrice,
                    quantity,
                    imageRes
            );
            CartManager.getInstance().addToCart(cartItem);
            Toast.makeText(ProductDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
        });


        if (FavoriteManager.getInstance().isFavorite(currentProduct)) {
            btnFav.setImageResource(R.drawable.like_filled);
        } else {
            btnFav.setImageResource(R.drawable.like);
        }


        btnFav.setOnClickListener(v -> {
            if (FavoriteManager.getInstance().isFavorite(currentProduct)) {
                FavoriteManager.getInstance().removeFavorite(currentProduct);
                btnFav.setImageResource(R.drawable.like);
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                FavoriteManager.getInstance().addFavorite(currentProduct);
                btnFav.setImageResource(R.drawable.like_filled);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTotalPrice() {
        double total = quantity * productPrice;
        tvTotalPrice.setText("$" + String.format("%.2f", total));
    }
}
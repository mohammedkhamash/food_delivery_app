package com.example.fooddelivery_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.Product;
import com.example.fooddelivery_app.ui.ProductDetails;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.FoodViewHolder> {

    public interface OnAddClickListener {
        void onAddClicked(Product product);
    }

    private Context context;
    private List<Product> productList;
    private OnAddClickListener addClickListener;

    // الكونستركتور القديم
    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.addClickListener = null;
    }

    // الكونستركتور الجديد مع Listener
    public ProductAdapter(Context context, List<Product> productList, OnAddClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.addClickListener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText("$" + product.getPrice());
        holder.tvRating.setText(String.valueOf(product.getRating()));
        holder.tvTime.setText(product.getTime() + " min");
        holder.imgFood.setImageResource(product.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetails.class);
            intent.putExtra(ProductDetails.EXTRA_PRODUCT_TITLE, product.getTitle());
            intent.putExtra(ProductDetails.EXTRA_PRODUCT_PRICE, product.getPrice());
            intent.putExtra(ProductDetails.EXTRA_PRODUCT_IMAGE, product.getImageResId());
            intent.putExtra(ProductDetails.EXTRA_PRODUCT_DESC, product.getDescription());
            intent.putExtra(ProductDetails.EXTRA_PRODUCT_RATING, product.getRating());
            intent.putExtra(ProductDetails.EXTRA_PRODUCT_TIME, product.getTime());
            context.startActivity(intent);
        });


        holder.btnAdd.setOnClickListener(v -> {
            if (addClickListener != null) {
                addClickListener.onAddClicked(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood, btnAdd;
        TextView tvTitle, tvPrice, tvRating, tvTime;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}
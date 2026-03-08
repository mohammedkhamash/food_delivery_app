package com.example.fooddelivery_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.CartItem;
import com.example.fooddelivery_app.utils.CartManager;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private CartListener listener;

    public interface CartListener {
        void onCartChanged();
    }

    // استخدام نسخة محلية من CartItems لتجنب مشاكل التزامن
    public CartAdapter(Context context, List<CartItem> cartItems, CartListener listener) {
        this.context = context;
        this.cartItems = new ArrayList<>(cartItems);
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));
        holder.tvSubPrice.setText(item.getQuantity() + " × $" + item.getPrice());
        holder.tvTotalPrice.setText("$" + String.format("%.2f", item.getTotalPrice()));
        holder.imgFood.setImageResource(item.getImageResId());

        // + زر زيادة الكمية
        holder.btnPlus.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            if (listener != null) listener.onCartChanged();
        });

        // - زر تقليل الكمية
        holder.btnMinus.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                if (listener != null) listener.onCartChanged();
            }
        });

        // حذف العنصر
        holder.btnDelete.setOnClickListener(v -> {
            CartManager.getInstance().removeFromCart(item);
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            if (listener != null) listener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood, btnDelete, btnMinus, btnPlus;
        TextView tvTitle, tvSubPrice, tvQuantity, tvTotalPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubPrice = itemView.findViewById(R.id.tvSubPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }
}
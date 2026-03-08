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

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ItemViewHolder> {

    private Context context;
    private List<CartItem> items;

    public OrderItemAdapter(Context context, List<CartItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_product, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.tvName.setText(item.getTitle());
        holder.tvQtyPrice.setText("Qty: " + item.getQuantity() + " × $" + item.getPrice());
        holder.imgProduct.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvQtyPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvQtyPrice = itemView.findViewById(R.id.tvProductQtyPrice);
        }
    }
}
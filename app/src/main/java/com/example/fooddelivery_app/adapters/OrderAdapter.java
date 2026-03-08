package com.example.fooddelivery_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tvDate.setText("Date: " + order.getDate());
        holder.tvTotal.setText("Total: $" + String.format("%.2f", order.getTotal()));

        // إعداد RecyclerView الداخلي
        OrderItemAdapter adapter = new OrderItemAdapter(context, order.getItems());
        holder.rvProducts.setLayoutManager(new LinearLayoutManager(context));
        holder.rvProducts.setAdapter(adapter);
    }

    @Override
    public int getItemCount() { return orders.size(); }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTotal;
        RecyclerView rvProducts;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvOrderDate);
            tvTotal = itemView.findViewById(R.id.tvOrderTotal);
            rvProducts = itemView.findViewById(R.id.rvOrderProducts);
        }
    }
}
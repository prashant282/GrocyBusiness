package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.OrderItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemsViewHolder> {

    Context context;
    ArrayList<OrderItemModel> items_list = new ArrayList();

    public OrderItemsAdapter(Context context, ArrayList<OrderItemModel> items_list) {
        this.context = context;
        this.items_list = items_list;
    }

    @NonNull
    @Override
    public OrderItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_items, parent, false);
        return new OrderItemsAdapter.OrderItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsViewHolder holder, int position) {
        OrderItemModel orderItemModel = items_list.get(position);

        holder.itemName.setText(orderItemModel.getItemsName());
        Glide.with(holder.itemImage.getContext()).load(orderItemModel.getItemsImage()).into(holder.itemImage);
        holder.itemCount.setText("" + orderItemModel.getItemCount());
        holder.itemQuantity.setText(orderItemModel.getItemsQuantity());
        holder.itemTotalPrice.setText("" + (orderItemModel.getItemCount() * Integer.parseInt(orderItemModel.getItemsPrice())));
    }

    @Override
    public int getItemCount() {
        return items_list.size();
    }

    public class OrderItemsViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageView itemImage;
        TextView itemCount;
        TextView itemQuantity;
        TextView itemTotalPrice;

        public OrderItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemImage = itemView.findViewById(R.id.item_image);
            itemCount = itemView.findViewById(R.id.item_count);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemTotalPrice = itemView.findViewById(R.id.item_total_price);
        }
    }
}

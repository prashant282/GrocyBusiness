package com.example.user.grocybusiness.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class OutOfStockItemAdapter extends FirestoreRecyclerAdapter<ItemModel, OutOfStockItemAdapter.MyViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OutOfStockItemAdapter(@NonNull FirestoreRecyclerOptions<ItemModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OutOfStockItemAdapter.MyViewHolder holder, int position, @NonNull ItemModel model) {
        holder.itemName.setText(model.getItemsProductName());
        holder.itemDes.setText(model.getItemsProductDescription());
        holder.itemPrice.setText(model.getItemsPrice());
        holder.itemQuantity.setText(model.getItemsQuantity());
        Glide.with(holder.itemImage.getContext()).load(model.getItemsImage()).into(holder.itemImage);
    }

    @NonNull
    @Override
    public OutOfStockItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_out_of_stock_items, parent, false);
        return new  OutOfStockItemAdapter.MyViewHolder(view);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDes, itemPrice, itemQuantity;
        ImageView itemImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemDes = itemView.findViewById(R.id.item_des);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_img);
            itemQuantity=itemView.findViewById(R.id.item_quantity);
        }
    }
}

package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemSearchViewHolder> {

    Context context;
    ArrayList<ItemModel> items_list;

    public ItemSearchAdapter(Context context, ArrayList<ItemModel> items_list) {
        this.context = context;
        this.items_list = items_list;
    }

    @NonNull
    @Override
    public ItemSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_item, parent, false);
        return new ItemSearchAdapter.ItemSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSearchViewHolder holder, int position) {
        ItemModel itemModel = items_list.get(position);


        holder.itemName.setText(itemModel.getItemsProductName());
        holder.itemDes.setText(itemModel.getItemsProductDescription());
        holder.itemPrice.setText(itemModel.getItemsPrice());
        holder.itemQuantity.setText(itemModel.getItemsQuantity());


        Glide.with(holder.itemImage.getContext()).load(itemModel.getItemsImage()).into(holder.itemImage);

//
//        holder.layout_search_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int parent_position = 0;
//                for (int i = 0; i < AllItemAdapter.items_list.size(); i++) {
//                    if (itemModel.getItemId().equals(AllItemAdapter.items_list.get(i).getItemId())) {
//                        parent_position = i;
//                    }
//                }
//                AllItemFragment.recyclerView.scrollToPosition(parent_position);
//                ItemsFragment.bottomSheetDialog.dismiss();
//
//            }
//        });

    }

    public void filteredList(ArrayList<ItemModel> filteredList) {
        items_list = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items_list.size();
    }

    public class ItemSearchViewHolder extends RecyclerView.ViewHolder {

        CardView layout_search_item;
        TextView itemName, itemDes, itemPrice, itemQuantity;
        ImageView itemImage;

        public ItemSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemDes = itemView.findViewById(R.id.item_des);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_img);
            itemQuantity = itemView.findViewById(R.id.item_quantity);

            layout_search_item = itemView.findViewById(R.id.layout_search_item);

        }
    }
}

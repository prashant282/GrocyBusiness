package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemDefaultImageModel;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDefaultImageAdapter extends RecyclerView.Adapter<ItemDefaultImageAdapter.ItemDefaultImageViewHolder> {

    Context context;
    ArrayList<ItemDefaultImageModel> items_image_list = new ArrayList();

    public ItemDefaultImageAdapter(Context context, ArrayList<ItemDefaultImageModel> items_image_list) {
        this.context = context;
        this.items_image_list = items_image_list;
    }

    @NonNull
    @Override
    public ItemDefaultImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_default_image, parent, false);
        return new ItemDefaultImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDefaultImageViewHolder holder, int position) {
        ItemDefaultImageModel itemDefaultImageModel = items_image_list.get(position);

        Glide.with(holder.item_image.getContext()).load(itemDefaultImageModel.getItemImage()).into(holder.item_image);

        if (itemDefaultImageModel.isSelected()) {

            holder.cardView.setStrokeWidth(1);

        } else {
            holder.cardView.setStrokeWidth(0);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.cardView.setStrokeWidth(1);
                for (int i = 0; i < items_image_list.size(); i++) {
                    if (items_image_list.get(i).isSelected()) {
                        items_image_list.get(i).setSelected(false);
                        notifyItemChanged(i);
                    }
                }
                items_image_list.get(position).setSelected(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items_image_list.size();
    }

    public class ItemDefaultImageViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        MaterialCardView cardView;

        public ItemDefaultImageViewHolder(@NonNull View itemView) {
            super(itemView);

            item_image = itemView.findViewById(R.id.item_image);
            cardView = itemView.findViewById(R.id.item_default_image_card);
        }
    }
}

package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.AddItemVariant;
import com.example.user.grocybusiness.activities.EditItemActivity;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.models.ItemModel;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class AllItemAdapter extends RecyclerView.Adapter<AllItemAdapter.AllItemViewHolder> {

    Context context;
    ArrayList<ItemModel> items_list;
    Bundle bundle;

    public AllItemAdapter(Context context, ArrayList<ItemModel> items_list) {
        this.context = context;
        this.items_list = items_list;
    }

    @NonNull
    @Override
    public AllItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_items, parent, false);
        return new AllItemAdapter.AllItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllItemViewHolder holder, int position) {

        ItemModel itemModel = items_list.get(position);
        bundle=new Bundle();
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=firebaseFirestore.collection("ShopsMain").document(MainActivity.selectedShop);


        holder.itemName.setText(itemModel.getItemsProductName());
        holder.itemDes.setText(itemModel.getItemsProductDescription());
        holder.itemPrice.setText(itemModel.getItemsPrice());
        holder.itemQuantity.setText(itemModel.getItemsQuantity());
        if (itemModel.getInStock()) {
            holder.btnSwitch.setChecked(true);
        } else {
            holder.btnSwitch.setChecked(false);
        }
        Glide.with(holder.itemImage.getContext()).load(itemModel.getItemsImage()).into(holder.itemImage);


        holder.btnSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                documentReference.collection("Items").document(itemModel.getItemId())
                        .update("inStock", true).addOnSuccessListener(aVoid -> {

                        }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
            } else {
                documentReference.collection("Items").document(itemModel.getItemId())
                        .update("inStock", false).addOnSuccessListener(aVoid -> {

                        }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });
        holder.btnAddVariant.setOnClickListener(view -> {

            Intent intent=new Intent(holder.btnAddVariant.getContext(),AddItemVariant.class);
            intent.putExtra("itemImage",itemModel.getItemsImage());
            holder.btnAddVariant.getContext().startActivity(intent);
        });


        holder.cardView.setOnLongClickListener(view -> {

            bundle.putString("itemCategory",itemModel.getItemCategory());
            bundle.putString("itemName", itemModel.getItemsProductName());
            bundle.putString("itemPrice", itemModel.getItemsPrice());
            bundle.putString("itemImage", itemModel.getItemsImage());
            bundle.putString("itemDes", itemModel.getItemsProductDescription());
            bundle.putString("itemQuantity", itemModel.getItemsQuantity());

            Intent intent =new Intent(holder.cardView.getContext(), EditItemActivity.class);
            intent.putExtras(bundle);
            holder.cardView.getContext().startActivity(intent);


            return false;
        });
    }

    @Override
    public int getItemCount() {
        return items_list.size();
    }


    public static class AllItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDes, itemPrice, itemQuantity;
        ImageView itemImage;
        SwitchMaterial btnSwitch;
        Button btnAddVariant;
        CardView cardView;

        public AllItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemDes = itemView.findViewById(R.id.item_des);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_img);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            btnSwitch = itemView.findViewById(R.id.btn_switch);
            btnAddVariant=itemView.findViewById(R.id.btn_add_variant);
            cardView=itemView.findViewById(R.id.layout_all_item);

        }
    }
}



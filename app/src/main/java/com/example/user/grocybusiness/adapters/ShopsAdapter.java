package com.example.user.grocybusiness.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.activities.MainActivity;
import com.example.user.grocybusiness.models.ShopsModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder> {
    Context context;
    ArrayList<ShopsModel> shops_list;

    public ShopsAdapter(Context context, ArrayList<ShopsModel> shops_list) {
        this.context = context;
        this.shops_list = shops_list;
    }

    @NonNull
    @Override
    public ShopsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_names, parent, false);
        return new ShopsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopsViewHolder holder, int position) {
        ShopsModel shopsModel = shops_list.get(position);

        holder.shop_letter.setText("" + shopsModel.getShopName().charAt(0));
        holder.shop_name.setText(shopsModel.getShopName());

        holder.shop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectedShop = shopsModel.getShopId();
                MainActivity.selectedIndex = position;
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("shopId", shopsModel.getShopId());
                intent.putExtra("shopIndex", position);
                context.startActivity(intent);
               ((Activity) holder.shop_name.getContext()).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return shops_list.size();
    }

    public class ShopsViewHolder extends RecyclerView.ViewHolder {

        TextView shop_letter;
        TextView shop_name;

        public ShopsViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_letter = itemView.findViewById(R.id.shop_letter);
            shop_name = itemView.findViewById(R.id.shop_name);

        }
    }
}

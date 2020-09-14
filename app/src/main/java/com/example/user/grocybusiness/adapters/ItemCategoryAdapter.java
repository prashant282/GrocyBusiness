package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemCategoryModel;
import com.example.user.grocybusiness.models.ItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ItemCategoryViewHolder> {

    public static AllItemAdapter allItemAdapter;
    Context context;
    ArrayList<ItemCategoryModel> category_wise_items_list = new ArrayList();

    public ItemCategoryAdapter(Context context, ArrayList<ItemCategoryModel> category_wise_items_list) {
        super();
        this.context = context;
        this.category_wise_items_list = category_wise_items_list;
    }

    @NonNull
    @Override
    public ItemCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_category, parent, false);
        return new ItemCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoryViewHolder holder, int position) {
        ItemCategoryModel shopItemsCategoryModel = this.category_wise_items_list.get(position);

        String item_category_name = shopItemsCategoryModel.getItem_category_name();
        ArrayList<ItemModel> singleItem = shopItemsCategoryModel.getItems_list();

        holder.item_category_name.setText(item_category_name);

        allItemAdapter = new AllItemAdapter(context, singleItem, category_wise_items_list, position);

        holder.all_item_recycler.setHasFixedSize(true);
        holder.all_item_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        holder.all_item_recycler.setAdapter(allItemAdapter);

    }

    @Override
    public int getItemCount() {
        return category_wise_items_list.size();
    }

    public class ItemCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView item_category_name;
        RecyclerView all_item_recycler;

        public ItemCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            item_category_name = itemView.findViewById(R.id.item_category_name);
            all_item_recycler = itemView.findViewById(R.id.all_item_recycler);
        }
    }
}

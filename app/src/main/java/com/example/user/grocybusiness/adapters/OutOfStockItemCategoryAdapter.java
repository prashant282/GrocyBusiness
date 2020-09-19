package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ItemCategoryModel;
import com.example.user.grocybusiness.models.ItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OutOfStockItemCategoryAdapter extends RecyclerView.Adapter<OutOfStockItemCategoryAdapter.OutOfStockItemCategoryViewHolder> {

    OutOfStockItemAdapter outOfStockItemAdapter;
    Context context;
    ArrayList<ItemCategoryModel> category_wise_items_list = new ArrayList();

    public OutOfStockItemCategoryAdapter(Context context, ArrayList<ItemCategoryModel> category_wise_items_list) {
        super();
        this.context = context;
        this.category_wise_items_list = category_wise_items_list;
    }


    @NonNull
    @Override
    public OutOfStockItemCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_category_out_of_stock, parent, false);
        return new OutOfStockItemCategoryAdapter.OutOfStockItemCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutOfStockItemCategoryViewHolder holder, int position) {

        ItemCategoryModel shopItemsCategoryModel = this.category_wise_items_list.get(position);

        String item_category_name = shopItemsCategoryModel.getItem_category_name();
        ArrayList<ItemModel> singleItem = shopItemsCategoryModel.getItems_list();
        int count = 0;
        for (int i = 0; i < singleItem.size(); i++) {
            if (singleItem.get(i).getInStock() == false) {
                count++;
            }
        }

        if (count >= 1) {

            holder.item_category_name.setText(item_category_name);

//            outOfStockItemAdapter = new OutOfStockItemAdapter(context, singleItem, category_wise_items_list, position);

            holder.all_item_recycler.setHasFixedSize(false);
            holder.all_item_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

            holder.all_item_recycler.setAdapter(outOfStockItemAdapter);

        } else {
            holder.itemView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }

    }

    @Override
    public int getItemCount() {
        return category_wise_items_list.size();
    }

    public class OutOfStockItemCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView item_category_name;
        RecyclerView all_item_recycler;

        public OutOfStockItemCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            item_category_name = itemView.findViewById(R.id.out_of_stock_item_category_name);
            all_item_recycler = itemView.findViewById(R.id.out_of_stock_item_recycler);
        }
    }
}

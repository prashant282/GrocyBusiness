package com.example.user.grocybusiness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ShopCategoryModel;

import java.util.ArrayList;


public class ShopCategoryAdapter extends ArrayAdapter<ShopCategoryModel> {
    TextView textViewName;
    public ShopCategoryAdapter(@NonNull Context context, ArrayList<ShopCategoryModel> categoryList) {
        super(context, 0, categoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_shop_category, parent, false
            );
        }

        ImageView imageViewFlag = convertView.findViewById(R.id.img_category);
        textViewName = convertView.findViewById(R.id.text_category);

        ShopCategoryModel currentItem = getItem(position);

        if (currentItem != null) {
            imageViewFlag.setImageResource(currentItem.getShopImage());
            textViewName.setText(currentItem.getShopCategory());
        }
        return convertView;

    }

}

package com.example.user.grocybusiness.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.grocybusiness.R;
import com.example.user.grocybusiness.models.ShopCategoryModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


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

    @SuppressLint("ResourceAsColor")
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
            Glide.with(convertView.getContext()).load(currentItem.getShopCategory()).into(imageViewFlag);
            textViewName.setText(currentItem.getShopCategory());
        }

        if (position == 0) {
            textViewName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_arrow_drop_down_24, 0);
            textViewName.setTextColor(R.color.hintColor);
        }

        return convertView;

    }

}

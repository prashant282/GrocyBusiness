package com.example.user.grocybusiness.models;

import java.util.ArrayList;

public class ItemCategoryModel {
    private String item_category_name;
    private ArrayList<ItemModel> items_list;

    public ItemCategoryModel() {
    }

    public ItemCategoryModel(String item_category_name, ArrayList<ItemModel> items_list) {
        this.item_category_name = item_category_name;
        this.items_list = items_list;
    }

    public String getItem_category_name() {
        return item_category_name;
    }

    public void setItem_category_name(String item_category_name) {
        this.item_category_name = item_category_name;
    }

    public ArrayList<ItemModel> getItems_list() {
        return items_list;
    }

    public void setItems_list(ArrayList<ItemModel> items_list) {
        this.items_list = items_list;
    }
}

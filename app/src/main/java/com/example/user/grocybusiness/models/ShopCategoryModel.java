package com.example.user.grocybusiness.models;

public class ShopCategoryModel {

    private String shopCategory;
    private int shopImage;

    public ShopCategoryModel(String shopCategory, int shopImage) {
        this.shopCategory = shopCategory;
        this.shopImage = shopImage;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public int getShopImage() {
        return shopImage;
    }
}

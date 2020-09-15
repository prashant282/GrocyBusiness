package com.example.user.grocybusiness.models;

public class ShopCategoryModel {

    private String shopCategory;
    private String shopImage;

    public ShopCategoryModel(String shopCategory, String shopImage) {
        this.shopCategory = shopCategory;
        this.shopImage = shopImage;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public String getShopImage() {
        return shopImage;
    }
}

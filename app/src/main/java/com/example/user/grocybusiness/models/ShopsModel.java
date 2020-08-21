package com.example.user.grocybusiness.models;

public class ShopsModel {

    String shopName;
    String shopCity;
    String shopCategory;
    String shopId;

    public ShopsModel() {
    }

    public ShopsModel(String shopName, String shopCity, String shopCategory, String shopId) {
        this.shopName = shopName;
        this.shopCity = shopCity;
        this.shopCategory = shopCategory;
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopCity() {
        return shopCity;
    }

    public void setShopCity(String shopCity) {
        this.shopCity = shopCity;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}

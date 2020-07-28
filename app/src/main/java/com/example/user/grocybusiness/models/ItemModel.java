package com.example.user.grocybusiness.models;

public class ItemModel {
    String itemsProductName, itemsProductDescription, itemsPrice, itemsImage, itemsQuantity;
    Boolean inStock;

    public ItemModel() {
    }

    public ItemModel(String itemsProductName, String itemsProductDescription, String itemsQuantity, String itemsPrice, String itemsImage, Boolean inStock) {
        this.itemsProductName = itemsProductName;
        this.itemsProductDescription = itemsProductDescription;
        this.itemsPrice = itemsPrice;
        this.itemsImage = itemsImage;
        this.itemsQuantity=itemsQuantity;
        this.inStock=inStock;
    }

    public String getItemsQuantity() {
        return itemsQuantity;
    }

    public String getItemsProductName() {
        return itemsProductName;
    }

    public String getItemsProductDescription() {
        return itemsProductDescription;
    }

    public String getItemsPrice() {
        return itemsPrice;
    }

    public String getItemsImage() {
        return itemsImage;
    }

    public Boolean getInStock() {
        return inStock;
    }
}

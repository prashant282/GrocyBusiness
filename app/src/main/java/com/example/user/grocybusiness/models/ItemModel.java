package com.example.user.grocybusiness.models;

public class ItemModel {
    String itemsProductName, itemsProductDescription, itemsPrice, itemsImage, itemsQuantity;
    Boolean inStock;

    String itemId;


    public ItemModel() {
    }

    public ItemModel(String itemsProductName, String itemsProductDescription, String itemsQuantity, String itemsPrice, String itemsImage, Boolean inStock) {
        this.itemsProductName = itemsProductName;
        this.itemsProductDescription = itemsProductDescription;
        this.itemsPrice = itemsPrice;
        this.itemsImage = itemsImage;
        this.itemsQuantity = itemsQuantity;
        this.inStock = inStock;
    }

    public String getItemsProductName() {
        return itemsProductName;
    }

    public void setItemsProductName(String itemsProductName) {
        this.itemsProductName = itemsProductName;
    }

    public String getItemsProductDescription() {
        return itemsProductDescription;
    }

    public void setItemsProductDescription(String itemsProductDescription) {
        this.itemsProductDescription = itemsProductDescription;
    }

    public String getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(String itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public String getItemsImage() {
        return itemsImage;
    }

    public void setItemsImage(String itemsImage) {
        this.itemsImage = itemsImage;
    }

    public String getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(String itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}

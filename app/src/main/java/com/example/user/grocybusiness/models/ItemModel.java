package com.example.user.grocybusiness.models;

import java.util.ArrayList;

public class ItemModel {
    String itemsProductName;
    String itemsProductDescription;
    String itemsPrice;
    String itemsImage;
    String itemsQuantity;

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    String itemCategory;
    Boolean inStock;

    String itemId;

    ArrayList<ItemVariantsModel> itemVariants;


    public ItemModel() {
    }

    public ItemModel(String itemsProductName, String itemsProductDescription, String itemsQuantity, String itemsPrice, String itemsImage, Boolean inStock, String itemCategory) {
        this.itemsProductName = itemsProductName;
        this.itemsProductDescription = itemsProductDescription;
        this.itemsPrice = itemsPrice;
        this.itemsImage = itemsImage;
        this.itemsQuantity = itemsQuantity;
        this.inStock = inStock;
        this.itemCategory=itemCategory;
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

    public ArrayList<ItemVariantsModel> getItemVariants() {
        return itemVariants;
    }

    public void setItemVariants(ArrayList<ItemVariantsModel> itemVariants) {
        this.itemVariants = itemVariants;
    }
}

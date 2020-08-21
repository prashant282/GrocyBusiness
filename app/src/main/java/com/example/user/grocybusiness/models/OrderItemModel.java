package com.example.user.grocybusiness.models;

public class OrderItemModel {
    long itemCount;
    String itemID;
    String itemsImage;
    String itemsName;
    String itemsPrice;
    String itemsQuantity;
    String shopId;
    String variantId;

    public OrderItemModel() {
    }

    public OrderItemModel(long itemCount, String itemID, String itemsImage, String itemsName, String itemsPrice, String itemsQuantity, String shopId, String variantId) {
        this.itemCount = itemCount;
        this.itemID = itemID;
        this.itemsImage = itemsImage;
        this.itemsName = itemsName;
        this.itemsPrice = itemsPrice;
        this.itemsQuantity = itemsQuantity;
        this.shopId = shopId;
        this.variantId = variantId;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemsImage() {
        return itemsImage;
    }

    public void setItemsImage(String itemsImage) {
        this.itemsImage = itemsImage;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(String itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public String getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(String itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }
}

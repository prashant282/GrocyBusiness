package com.example.user.grocybusiness.models;

public class ItemVariantsModel {

    String itemPrice;
    String itemQuantity;
    boolean checked;
    String itemID;
    int variant_count;
    String variant_id;
    boolean inStock;

    public ItemVariantsModel(String itemPrice, String itemQuantity, boolean checked, String itemID, int variant_count, String variant_id, boolean inStock) {
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.checked = checked;
        this.itemID = itemID;
        this.variant_count = variant_count;
        this.variant_id = variant_id;
        this.inStock = inStock;
    }

    public ItemVariantsModel() {

    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getVariant_count() {
        return variant_count;
    }

    public void setVariant_count(int variant_count) {
        this.variant_count = variant_count;
    }

    public String getVariant_id() {
        return variant_id;
    }

    public void setVariant_id(String variant_id) {
        this.variant_id = variant_id;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

package com.example.user.grocybusiness.models;

public class ItemDefaultImageModel {
    String itemImage;
    boolean selected = false;

    public ItemDefaultImageModel() {
    }

    public ItemDefaultImageModel(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

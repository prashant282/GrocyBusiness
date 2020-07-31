package com.example.user.grocybusiness.models;

public class NotificationModel {

    String userName, userAddress, dateTime, userImage, orderNumberId;
    boolean freshNotification;

    public NotificationModel(String userName, String userAddress, String dateTime, String userImage, boolean freshNotification, String orderNumberId) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.dateTime = dateTime;
        this.userImage = userImage;
        this.freshNotification=freshNotification;

    }

    public NotificationModel() {
    }

    public boolean isFreshNotification() {
        return freshNotification;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getOrderNumberId() {
        return orderNumberId;
    }
}

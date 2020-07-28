package com.example.user.grocybusiness.models;

public class NotificationModel {

    String userName, userAddress, dateTime, userImage;

    public NotificationModel(String userName, String userAddress, String dateTime, String userImage) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.dateTime = dateTime;
        this.userImage = userImage;
    }

    public NotificationModel() {
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
}

package com.example.user.grocybusiness.models;

//public class OrdersAllModel {
//
//    String orderId;
//    String orderTime;
//    String orderStatus;
//    String userDetails;
//    String deliveryRemainingTime;
//    String orderDetails;
//    String orderPrice;
//    String orderPaymentStatus;
//    String deliveryImage;
//    String deliveryName;
//    String deliveryMsg;
//    String deliveryBoyRemainingTime;
//    String deliveryOTP;
//    String changeOrderState;
//    String orderDocumentId;
//
//    public OrdersAllModel() {
//
//    }
//
//    public OrdersAllModel(String orderId, String orderTime, String orderStatus, String userDetails, String deliveryRemainingTime, String orderDetails, String orderPrice, String orderPaymentStatus, String deliveryImage, String deliveryName, String deliveryMsg, String deliveryBoyRemainingTime, String deliveryOTP, String changeOrderState) {
//        this.orderId = orderId;
//        this.orderTime = orderTime;
//        this.orderStatus = orderStatus;
//        this.userDetails = userDetails;
//        this.deliveryRemainingTime = deliveryRemainingTime;
//        this.orderDetails = orderDetails;
//        this.orderPrice = orderPrice;
//        this.orderPaymentStatus = orderPaymentStatus;
//        this.deliveryImage = deliveryImage;
//        this.deliveryName = deliveryName;
//        this.deliveryMsg = deliveryMsg;
//        this.deliveryBoyRemainingTime = deliveryBoyRemainingTime;
//        this.deliveryOTP = deliveryOTP;
//        this.changeOrderState = changeOrderState;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getOrderTime() {
//        return orderTime;
//    }
//
//    public void setOrderTime(String orderTime) {
//        this.orderTime = orderTime;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//
//    public String getUserDetails() {
//        return userDetails;
//    }
//
//    public void setUserDetails(String userDetails) {
//        this.userDetails = userDetails;
//    }
//
//    public String getDeliveryRemainingTime() {
//        return deliveryRemainingTime;
//    }
//
//    public void setDeliveryRemainingTime(String deliveryRemainingTime) {
//        this.deliveryRemainingTime = deliveryRemainingTime;
//    }
//
//    public String getOrderDetails() {
//        return orderDetails;
//    }
//
//    public void setOrderDetails(String orderDetails) {
//        this.orderDetails = orderDetails;
//    }
//
//    public String getOrderPrice() {
//        return orderPrice;
//    }
//
//    public void setOrderPrice(String orderPrice) {
//        this.orderPrice = orderPrice;
//    }
//
//    public String getOrderPaymentStatus() {
//        return orderPaymentStatus;
//    }
//
//    public void setOrderPaymentStatus(String orderPaymentStatus) {
//        this.orderPaymentStatus = orderPaymentStatus;
//    }
//
//    public String getDeliveryImage() {
//        return deliveryImage;
//    }
//
//    public void setDeliveryImage(String deliveryImage) {
//        this.deliveryImage = deliveryImage;
//    }
//
//    public String getDeliveryName() {
//        return deliveryName;
//    }
//
//    public void setDeliveryName(String deliveryName) {
//        this.deliveryName = deliveryName;
//    }
//
//    public String getDeliveryMsg() {
//        return deliveryMsg;
//    }
//
//    public void setDeliveryMsg(String deliveryMsg) {
//        this.deliveryMsg = deliveryMsg;
//    }
//
//    public String getDeliveryBoyRemainingTime() {
//        return deliveryBoyRemainingTime;
//    }
//
//    public void setDeliveryBoyRemainingTime(String deliveryBoyRemainingTime) {
//        this.deliveryBoyRemainingTime = deliveryBoyRemainingTime;
//    }
//
//    public String getDeliveryOTP() {
//        return deliveryOTP;
//    }
//
//    public void setDeliveryOTP(String deliveryOTP) {
//        this.deliveryOTP = deliveryOTP;
//    }
//
//    public String getChangeOrderState() {
//        return changeOrderState;
//    }
//
//    public void setChangeOrderState(String changeOrderState) {
//        this.changeOrderState = changeOrderState;
//    }
//
//    public String getOrderDocumentId() {
//        return orderDocumentId;
//    }
//
//    public void setOrderDocumentId(String orderDocumentId) {
//        this.orderDocumentId = orderDocumentId;
//    }
//}

public class OrdersAllModel {
    String orderId;
    String orderNumberId;
    String dateTime;
    double itemAmount;
    double orderAmount;
    String orderStatus;
    String shopId;
    String shopImage;
    String shopKeeperId;
    String shopName;
    double taxAmount;
    String userAddress;
    String userImage;
    String userName;

    public OrdersAllModel() {
    }

    public OrdersAllModel(String orderId, String orderNumberId, String dateTime, double itemAmount, double orderAmount, String orderStatus, String shopId, String shopImage, String shopKeeperId, String shopName, double taxAmount, String userAddress, String userImage, String userName) {
        this.orderId = orderId;
        this.orderNumberId = orderNumberId;
        this.dateTime = dateTime;
        this.itemAmount = itemAmount;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.shopId = shopId;
        this.shopImage = shopImage;
        this.shopKeeperId = shopKeeperId;
        this.shopName = shopName;
        this.taxAmount = taxAmount;
        this.userAddress = userAddress;
        this.userImage = userImage;
        this.userName = userName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumberId() {
        return orderNumberId;
    }

    public void setOrderNumberId(String orderNumberId) {
        this.orderNumberId = orderNumberId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopKeeperId() {
        return shopKeeperId;
    }

    public void setShopKeeperId(String shopKeeperId) {
        this.shopKeeperId = shopKeeperId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
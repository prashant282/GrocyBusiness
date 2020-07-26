package com.example.user.grocybusiness.models;

public class OrdersAllModel {

    String orderId;
    String orderTime;
    String orderStatus;
    String userDetails;
    String deliveryRemainingTime;
    String orderDetails;
    String orderPrice;
    String orderPaymentStatus;
    String deliveryImage;
    String deliveryName;
    String deliveryMsg;
    String deliveryBoyRemainingTime;
    String deliveryOTP;
    String changeOrderState;

    public OrdersAllModel() {

    }

    public OrdersAllModel(String orderId, String orderTime, String orderStatus, String userDetails, String deliveryRemainingTime, String orderDetails, String orderPrice, String orderPaymentStatus, String deliveryImage, String deliveryName, String deliveryMsg, String deliveryBoyRemainingTime, String deliveryOTP, String changeOrderState) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.userDetails = userDetails;
        this.deliveryRemainingTime = deliveryRemainingTime;
        this.orderDetails = orderDetails;
        this.orderPrice = orderPrice;
        this.orderPaymentStatus = orderPaymentStatus;
        this.deliveryImage = deliveryImage;
        this.deliveryName = deliveryName;
        this.deliveryMsg = deliveryMsg;
        this.deliveryBoyRemainingTime = deliveryBoyRemainingTime;
        this.deliveryOTP = deliveryOTP;
        this.changeOrderState = changeOrderState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public String getDeliveryRemainingTime() {
        return deliveryRemainingTime;
    }

    public void setDeliveryRemainingTime(String deliveryRemainingTime) {
        this.deliveryRemainingTime = deliveryRemainingTime;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public String getDeliveryImage() {
        return deliveryImage;
    }

    public void setDeliveryImage(String deliveryImage) {
        this.deliveryImage = deliveryImage;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMsg() {
        return deliveryMsg;
    }

    public void setDeliveryMsg(String deliveryMsg) {
        this.deliveryMsg = deliveryMsg;
    }

    public String getDeliveryBoyRemainingTime() {
        return deliveryBoyRemainingTime;
    }

    public void setDeliveryBoyRemainingTime(String deliveryBoyRemainingTime) {
        this.deliveryBoyRemainingTime = deliveryBoyRemainingTime;
    }

    public String getDeliveryOTP() {
        return deliveryOTP;
    }

    public void setDeliveryOTP(String deliveryOTP) {
        this.deliveryOTP = deliveryOTP;
    }

    public String getChangeOrderState() {
        return changeOrderState;
    }

    public void setChangeOrderState(String changeOrderState) {
        this.changeOrderState = changeOrderState;
    }
}

package com.example.jrsl;

import java.util.Date;

public class OrderDetailsItem {
    private String orderDate;
    private String orderRef;
    private String orderStatus;
    private String orderImageURL;

    private String orderAddress;

    private double shipping;
    private Double orderTotal;


    OrderDetailsItem(String orderDate,String orderRef,String orderStatus, String orderImageURL,Double orderTotal){
        this.orderDate = orderDate;
        this.orderRef = orderRef;
        this.orderStatus = orderStatus;
        this.orderImageURL = orderImageURL;
        this.orderTotal = orderTotal;
    }

    // receipt
    OrderDetailsItem(String orderAddress, Double orderTotal, String orderDate,String orderRef, double shipping){
        this.orderDate = orderDate;
        this.orderRef = orderRef;
        this.shipping = shipping;
        this.orderAddress = orderAddress;
        this.orderTotal = orderTotal;
    }

    public OrderDetailsItem() {

    }

    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderImageURL() {
        return orderImageURL;
    }

    public void setOrderImageURL(String orderImageURL) {
        this.orderImageURL = orderImageURL;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }
}

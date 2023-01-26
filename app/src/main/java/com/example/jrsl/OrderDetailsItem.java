package com.example.jrsl;

import java.util.Date;

public class OrderDetailsItem {
    private String orderDate;
    private String orderRef;
    private String orderStatus;
    private String orderImageURL;

    OrderDetailsItem(String orderDate,String orderRef,String orderStatus, String orderImageURL){
        this.orderDate = orderDate;
        this.orderRef = orderRef;
        this.orderStatus = orderStatus;
        this.orderImageURL = orderImageURL;
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

    public void setOrderImage(String orderImageURL) {
        this.orderImageURL = orderImageURL;
    }
}

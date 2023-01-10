package com.example.jrsl;

import java.util.Date;

public class OrderDetailsItem {
    private String orderDate;
    private String orderRef;
    private String orderStatus;
    private int orderImage;

    OrderDetailsItem(String orderDate,String orderRef,String orderStatus, int orderImage){
        this.orderDate = orderDate;
        this.orderRef = orderRef;
        this.orderStatus = orderStatus;
        this.orderImage = orderImage;
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

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }
}

package com.bawei.shop2019101511.bean;

import java.util.ArrayList;

public class OrderBean {
    private ArrayList<OrderResult> orderList;
    private String message;
    private String status;

    public OrderBean(String message) {
        this.message = message;
    }

    public ArrayList<OrderResult> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<OrderResult> orderList) {
        this.orderList = orderList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

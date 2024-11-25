package com.example.mymushroomf.PembeliModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private String orderId;
    private List<OrderDetail> orderDetails;

    public Order(String orderId, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
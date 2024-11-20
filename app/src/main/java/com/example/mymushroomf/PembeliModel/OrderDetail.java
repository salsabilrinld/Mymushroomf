package com.example.mymushroomf.PembeliModel;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail implements Serializable {
    private Produk1 product;
    private String orderDetailId;
    private String status;
    private String shippingMethod;
    private String trackingNumber;
    private String address;
    private String recipientName;
    private String recipientPhone;
    private String paymentMethod;
    private Date orderDate;
    private int quantity;
    private int productCost;
    private int shippingCost;
    private int totalPayment;

    public OrderDetail(String orderDetailId, Produk1 product, String status, String shippingMethod, String trackingNumber,
                       String address, String recipientName, String recipientPhone, Date orderDate, int quantity,
                       int productCost, int shippingCost, int totalPayment) {
        this.orderDetailId = orderDetailId;
        this.product = product;
        this.status = status;
        this.shippingMethod = shippingMethod;
        this.trackingNumber = trackingNumber;
        this.address = address;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.productCost = productCost;
        this.shippingCost = shippingCost;
        this.totalPayment = totalPayment;
    }

    // Getter methods
    public String getOrderDetailId() {
        return orderDetailId;
    }

    public Produk1 getProduct() {
        return product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getProductCost() {
        return productCost;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setProduct(Produk1 product) {
        this.product = product;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }
}

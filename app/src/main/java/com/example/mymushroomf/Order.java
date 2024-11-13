package com.example.mymushroomf;

import java.io.Serializable;

public class Order implements Serializable {
    private String productName, buyerName, productImage, productQuantity, orderId, orderStatus, productInfo, orderCourier, orderResi, orderAddress, orderMethod, orderCostProduct, orderCostDelivery, orderTotal;

    public Order (String productName, String buyerName, String productImage, String productQuantity, String orderStatus, String orderTotal) {
        this.productName = productName;
        this.buyerName = buyerName;
        this.productImage = productImage;
        this.productQuantity = productQuantity;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }

    public Order(String orderId, String orderStatus, String productInfo, String orderCourier, String orderResi, String orderAddress, String orderMethod, String orderCostProduct, String orderCostDelivery, String orderTotal) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.productInfo = productInfo;
        this.orderCourier = orderCourier;
        this.orderResi = orderResi;
        this.orderAddress = orderAddress;
        this.orderMethod = orderMethod;
        this.orderCostProduct = orderCostProduct;
        this.orderCostDelivery = orderCostDelivery;
        this.orderTotal = orderTotal;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getOrderCourier() {
        return orderCourier;
    }

    public void setOrderCourier(String orderCourier) {
        this.orderCourier = orderCourier;
    }

    public String getOrderResi() {
        return orderResi;
    }

    public void setOrderResi(String orderResi) {
        this.orderResi = orderResi;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }

    public String getOrderCostProduct() {
        return orderCostProduct;
    }

    public void setOrderCostProduct(String orderCostProduct) {
        this.orderCostProduct = orderCostProduct;
    }

    public String getOrderCostDelivery() {
        return orderCostDelivery;
    }

    public void setOrderCostDelivery(String orderCostDelivery) {
        this.orderCostDelivery = orderCostDelivery;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

}

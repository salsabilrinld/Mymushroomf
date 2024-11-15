package com.example.mymushroomf;

import java.io.Serializable;

public class Order implements Serializable {
    private String productName, buyerName, productImage, orderStatus, productInfo, orderCourier, orderResi, orderAddress, orderMethod, orderCostProduct, orderCostDelivery;
    private int productQuantity;
    private double orderTotal;

    // Constructor for initial order data (with quantity and total)
    public Order(String productName, String buyerName, String productImage, int productQuantity, String orderStatus, double orderTotal) {
        this.productName = productName;
        this.buyerName = buyerName;
        this.productImage = productImage; // store image name (resource name)
        this.productQuantity = productQuantity;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }

    public Order(String jamurTiram, String salsabilrinld, int jamurTiram1, String s, String selesai, String s1) {
    }

    public Order(String number, String selesai, String jamurTiram, String reguler, String number1, String perumahanBogor, String bca, String s, String s1, String s2) {
    }

    // Getters and Setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public String getProductImage() { return productImage; } // image name
    public void setProductImage(String productImage) { this.productImage = productImage; }

    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getProductInfo() { return productInfo; }
    public void setProductInfo(String productInfo) { this.productInfo = productInfo; }

    public String getOrderCourier() { return orderCourier; }
    public void setOrderCourier(String orderCourier) { this.orderCourier = orderCourier; }

    public String getOrderResi() { return orderResi; }
    public void setOrderResi(String orderResi) { this.orderResi = orderResi; }

    public String getOrderAddress() { return orderAddress; }
    public void setOrderAddress(String orderAddress) { this.orderAddress = orderAddress; }

    public String getOrderMethod() { return orderMethod; }
    public void setOrderMethod(String orderMethod) { this.orderMethod = orderMethod; }

    public String getOrderCostProduct() { return orderCostProduct; }
    public void setOrderCostProduct(String orderCostProduct) { this.orderCostProduct = orderCostProduct; }

    public String getOrderCostDelivery() { return orderCostDelivery; }
    public void setOrderCostDelivery(String orderCostDelivery) { this.orderCostDelivery = orderCostDelivery; }

    public double getOrderTotal() { return orderTotal; }
    public void setOrderTotal(double orderTotal) { this.orderTotal = orderTotal; }
}

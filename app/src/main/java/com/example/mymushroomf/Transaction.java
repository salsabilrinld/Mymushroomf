package com.example.mymushroomf;

public class Transaction {
        private String fungiName;
        private String fungiQuantity;
        private String transactionStatus;
        private String fungiPrice;
        private String fungiImageUrl;

    public Transaction(String fungiName, String fungiQuantity, String transactionStatus, String fungiPrice, String fungiImageUrl) {
        this.fungiName = fungiName;
        this.fungiQuantity = fungiQuantity;
        this.transactionStatus = transactionStatus;
        this.fungiPrice = fungiPrice;
        this.fungiImageUrl = fungiImageUrl;
    }

    public String getFungiName() {
        return fungiName;
    }

    public void setFungiName(String fungiName) {
        this.fungiName = fungiName;
    }

    public String getFungiQuantity() {
        return fungiQuantity;
    }

    public void setFungiQuantity(String fungiQuantity) {
        this.fungiQuantity = fungiQuantity;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getFungiPrice() {
        return fungiPrice;
    }

    public void setFungiPrice(String fungiPrice) {
        this.fungiPrice = fungiPrice;
    }

    public String getFungiImageUrl() {
        return fungiImageUrl;
    }

    public void setFungiImageUrl(String fungiImageUrl) {
        this.fungiImageUrl = fungiImageUrl;
    }
}

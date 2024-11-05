package com.example.mymushroomf;

public class Address {
    private String name;
    private String phoneNumber;
    private String addressLine;
    private String type;

    public Address(String name, String phoneNumber, String address, String type) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressLine = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return addressLine;
    }

    public String getType() {
        return type;
    }
}

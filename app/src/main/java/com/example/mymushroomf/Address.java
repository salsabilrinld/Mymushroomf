package com.example.mymushroomf;

public class Address {
    private String name;
    private String phoneNumber;
    private String address;
    private String type;

    public Address(String name, String phoneNumber, String address, String type) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
}

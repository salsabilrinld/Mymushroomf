package com.example.mymushroomf.PembeliModel;

import java.io.Serializable;

public class Address implements Serializable {
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
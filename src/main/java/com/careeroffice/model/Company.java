package com.careeroffice.model;

public class Company {

    private String id;
    private String title;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;

    public Company(String id, String title, String address, String phoneNumber, String email, String website) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }
}

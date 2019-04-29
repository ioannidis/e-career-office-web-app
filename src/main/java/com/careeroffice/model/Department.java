package com.careeroffice.model;

public class Department {

    private String id;
    private String title;

    public Department(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

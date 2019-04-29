package com.careeroffice.model;

public class CV {
    private int id;
    private String username;
    private String fileUrl;

    public CV(int id, String username, String fileUrl) {
        this.id = id;
        this.username = username;
        this.fileUrl = fileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}

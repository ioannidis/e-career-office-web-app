package com.careeroffice.model;

public class Cv {

    private int id;

    private String username;

    private String fileUrl;

    public Cv() {

    }

    public Cv(String username, String fileUrl) {
        this.username = username;
        this.fileUrl = fileUrl;
    }

    public Cv(int id, String username, String fileUrl) {
        this.id = id;
        this.username = username;
        this.fileUrl = fileUrl;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    @Override
    public String toString() {
        return "CV{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}

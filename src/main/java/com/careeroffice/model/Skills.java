package com.careeroffice.model;

public class Skills {

    private String username;
    private String skills;

    public Skills(){}

    public  Skills(String username , String skills){
        this.username = username;
        this.skills = skills;
    }

    public String getUsername() {
        return username;
    }

    public String getSkills() {
        return skills;
    }
}

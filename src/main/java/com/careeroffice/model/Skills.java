package com.careeroffice.model;

public class Skills {

    private String username;
    private String skills;
    private String slug;

    public Skills(){}

    public  Skills(String username , String skills, String slug){
        this.username = username;
        this.skills = skills;
        this.slug = slug;
    }

    public String getUsername() {
        return username;
    }

    public String getSkills() {
        return skills;
    }

    public String getSlug() {
        return slug;
    }
}

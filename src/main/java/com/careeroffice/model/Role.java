package com.careeroffice.model;

public final class Role {
    public static final String SuperAdmin = "super_admin";
    public static final String Admin = "admin";
    public static final String External = "external";
    public static final String Instructor = "instructor";
    public static final String PostgraduateStudent = "p_student";
    public static final String UndergraduateStudent = "u_student";

    private String id;
    private String title;

    public Role(String id, String title) {
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

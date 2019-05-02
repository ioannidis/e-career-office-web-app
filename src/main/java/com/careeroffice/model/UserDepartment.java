package com.careeroffice.model;

public class UserDepartment {

    private String username;
    private String departmentId;

    public UserDepartment(String username, String departmentId) {
        this.username = username;
        this.departmentId = departmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}

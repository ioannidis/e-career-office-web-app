package com.careeroffice.model;

public class UserCompany {

    private String username;
    private String companyId;

    public UserCompany(String username, String companyId) {
        this.username = username;
        this.companyId = companyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}

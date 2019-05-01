package com.careeroffice.model;

public class Classified {

    private int id;

    private String title;

    private String content;

    private String companyId;

    private int categoryId;

    public Classified() {
    }

    public Classified( String title, String content, String companyId, int categoryId ) {
        this.title = title;
        this.content = content;
        this.companyId = companyId;
        this.categoryId = categoryId;
    }

    public Classified( int id, String title, String content, String companyId, int categoryId ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.companyId = companyId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId( String companyId ) {
        this.companyId = companyId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId( int categoryId ) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Classified{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", companyId=" + companyId +
                ", categoryId=" + categoryId +
                '}';
    }
}

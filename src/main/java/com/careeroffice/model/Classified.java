package com.careeroffice.model;

import java.util.List;

public class Classified {

    private int id;

    private String title;

    private String content;

    private String companyId;

    private int categoryId;

    private Company company;
    private Category category;
    private List<KeywordClassifiedPivot> keywordClassifiedPivotList;

    public Classified() {
    }

    public Classified(String title, String content, String companyId, int categoryId) {
        this.title = title;
        this.content = content;
        this.companyId = companyId;
        this.categoryId = categoryId;
    }

    public Classified(int id, String title, String content, String companyId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.companyId = companyId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<KeywordClassifiedPivot> getKeywordClassifiedPivotList() {
        return keywordClassifiedPivotList;
    }

    public void setKeywordClassifiedPivotList(List<KeywordClassifiedPivot> keywordClassifiedPivotList) {
        this.keywordClassifiedPivotList = keywordClassifiedPivotList;
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

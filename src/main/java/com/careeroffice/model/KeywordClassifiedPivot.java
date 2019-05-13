package com.careeroffice.model;

public class KeywordClassifiedPivot {

    private int keywordId;

    private int classifiedId;

    private Keyword keyword;
    private Classified classified;

    public KeywordClassifiedPivot() {
    }

    public KeywordClassifiedPivot(int keywordId, int classifiedId) {
        this.keywordId = keywordId;
        this.classifiedId = classifiedId;
    }

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    public int getClassifiedId() {
        return classifiedId;
    }

    public void setClassifiedId(int classifiedId) {
        this.classifiedId = classifiedId;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Classified getClassified() {
        return classified;
    }

    public void setClassified(Classified classified) {
        this.classified = classified;
    }
}

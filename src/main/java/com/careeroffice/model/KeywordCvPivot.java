package com.careeroffice.model;

public class KeywordCvPivot {

    private int keywordId;

    private int cvId;

    public KeywordCvPivot() {
    }

    public KeywordCvPivot( int keywordId, int cvId ) {
        this.keywordId = keywordId;
        this.cvId = cvId;
    }

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId( int keywordId ) {
        this.keywordId = keywordId;
    }

    public int getCvId() {
        return cvId;
    }

    public void setCvId( int cvId ) {
        this.cvId = cvId;
    }
}

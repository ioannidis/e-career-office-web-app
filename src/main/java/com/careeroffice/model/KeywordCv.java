package com.careeroffice.model;

public class KeywordCv {

    private int keywordId;

    private int CvId;

    public KeywordCv() {
    }

    public KeywordCv( int keywordId, int CvId ) {
        this.keywordId = keywordId;
        this.CvId = CvId;
    }

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId( int keywordId ) {
        this.keywordId = keywordId;
    }

    public int getCvId() {
        return CvId;
    }

    public void setCvId( int classifiedId ) {
        this.CvId = classifiedId;
    }
}

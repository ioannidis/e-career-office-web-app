package com.careeroffice.model.decorator;

import com.careeroffice.model.Classified;
import com.careeroffice.model.Keyword;

import java.util.List;

public class ClassifiedKeywordDecorator {
    private Classified classified;

    private List<Keyword> keywords;

    public ClassifiedKeywordDecorator( Classified classified ) {
        this.classified = classified;
    }

    public Classified getClassified() {
        return classified;
    }

    public void setClassified( Classified classified ) {
        this.classified = classified;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords( List<Keyword> keywords ) {
        this.keywords = keywords;
    }
}

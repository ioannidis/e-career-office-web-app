package com.careeroffice.model.decorator;

import com.careeroffice.model.Keyword;
import com.careeroffice.model.User;

import java.util.List;

public class UserKeywordDecorator {

    private User user;

    private List<Keyword> keywords;

    public UserKeywordDecorator( User user ) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords( List<Keyword> keywords ) {
        this.keywords = keywords;
    }
}

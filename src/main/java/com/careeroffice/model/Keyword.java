package com.careeroffice.model;

public class Keyword {

    private int id;

    private String title;

    private String slug;

    public Keyword() {
    }

    public Keyword(String title, String slug ) {
        this.title = title;
        this.slug = slug;
    }

    public Keyword( int id, String title, String slug ) {
        this.id = id;
        this.title = title;
        this.slug = slug;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug( String slug ) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Keywords{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Keyword.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Keyword other = (Keyword) obj;
        if (this.id != other.getId()) {
            return false;
        }

        return true;
    }

}

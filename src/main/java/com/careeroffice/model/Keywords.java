package com.careeroffice.model;

public class Keywords {

    private int id;

    private String title;

    private String slug;

    public Keywords() {
    }

    public Keywords( int id, String title, String slug ) {
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
}

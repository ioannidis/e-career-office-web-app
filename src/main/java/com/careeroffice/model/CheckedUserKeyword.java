package com.careeroffice.model;

public class CheckedUserKeyword extends Keyword {

    public boolean checked;

    public CheckedUserKeyword(String title, String slug, boolean checked) {
        super(title, slug);
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Keywords{" +
                "id=" + super.getId() +
                ", title='" + super.getTitle() + '\'' +
                ", slug='" + super.getSlug() + '\'' +
                ", checked='" + isChecked() + '\'' +
                '}';
    }
}

package com.onedev.englishlearning.data.model;

public class MainLibrary {

    private int resId;
    private String title;
    private String description;
    private int dbNumber;

    public MainLibrary(int resId, String title, String description, int dbNumber) {
        this.resId = resId;
        this.title = title;
        this.description = description;
        this.dbNumber = dbNumber;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }
}

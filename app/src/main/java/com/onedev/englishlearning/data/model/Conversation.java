package com.onedev.englishlearning.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conversation {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("person")
    @Expose
    private String person;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("category_id")
    @Expose
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

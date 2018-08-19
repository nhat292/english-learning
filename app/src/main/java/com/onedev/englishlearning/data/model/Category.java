package com.onedev.englishlearning.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("topic_id")
    @Expose
    private int topicId;

    @SerializedName("is_added_favorites")
    @Expose
    private boolean addedFavorites;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public boolean isAddedFavories() {
        return addedFavorites;
    }

    public void setAddedFavorites(boolean addedFavorites) {
        this.addedFavorites = addedFavorites;
    }
}

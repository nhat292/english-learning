package com.onedev.englishlearning.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.onedev.englishlearning.data.model.Category;

import java.util.ArrayList;

/**
 * Created by Nguyen Van Nhat on 8/20/2018
 */
public class FavoritesResponse {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private FavoritesData favoritesData;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FavoritesData getFavoritesData() {
        return favoritesData;
    }

    public void setFavoritesData(FavoritesData favoritesData) {
        this.favoritesData = favoritesData;
    }

    public static class FavoritesData {

        @SerializedName("categories1")
        @Expose
        private ArrayList<Category> categories1;

        @SerializedName("categories2")
        @Expose
        private ArrayList<Category> categories2;

        public ArrayList<Category> getCategories1() {
            return categories1;
        }

        public void setCategories1(ArrayList<Category> categories1) {
            this.categories1 = categories1;
        }

        public ArrayList<Category> getCategories2() {
            return categories2;
        }

        public void setCategories2(ArrayList<Category> categories2) {
            this.categories2 = categories2;
        }
    }
}

package com.example.newestapp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FullItem {
    private int imageResourse;
    private String title;
    private String date;
    private String key_id;
    private String favStatus;

    public FullItem(){

    }

    public FullItem(int imageResourse, String title, String date, String key_id, String favStatus){
        this.imageResourse = imageResourse;
        this.title = title;
        this.date = date;
        this.key_id = key_id;
        this.favStatus = favStatus;
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public void setImageResourse(int imageResourse) {
        this.imageResourse = imageResourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
package com.example.newestapp;

public class FavItem {

    private String item_title;
    private String item_date;
    private String key_id;
    private int item_image;

    public FavItem() {
    }

    public FavItem(String item_title, String item_date, String key_id, int item_image) {
        this.item_title = item_title;
        this.item_date = item_date;
        this.key_id = key_id;
        this.item_image = item_image;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }
}

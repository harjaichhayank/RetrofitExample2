package com.example.retrofitExample2;

import com.google.gson.annotations.SerializedName;

class Post {
    private int userId;
    private Integer id;
    private String title;

    @SerializedName("body")
    private String text;

    Post(int userId, String title, String text) {
        setUserId(userId);
        setTitle(title);
        setText(text);
    }

    String getTitle() { return title; }
    int getUserId() {
        return userId;
    }
    String getText() {
        return text;
    }
    Integer getId() { return id; }

    private void setUserId(int userId) { this.userId = userId; }
    public void setId(int id) { this.id = id; }
    private void setTitle(String title) { this.title = title; }
    private void setText(String text) { this.text = text; }

}

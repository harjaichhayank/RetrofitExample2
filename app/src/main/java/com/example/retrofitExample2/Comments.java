package com.example.retrofitExample2;

import com.google.gson.annotations.SerializedName;

class Comments {
    private int postId;
    private Integer id;
    private String name;
    private String email;

    @SerializedName("body")
    private String text;

    int getPostId() {
        return postId;
    }

    Integer getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getText() {
        return text;
    }
}


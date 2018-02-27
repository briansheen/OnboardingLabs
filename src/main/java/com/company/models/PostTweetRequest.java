package com.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostTweetRequest {

    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

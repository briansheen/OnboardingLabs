package com.company.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetResponse {

    private String content;

    public TweetResponse() {
    }

    public TweetResponse(String content) {
        this.content = content;
    }

    @JsonProperty
    public String getContent(){
        return content;
    }

}

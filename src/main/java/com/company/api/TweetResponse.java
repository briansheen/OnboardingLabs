package com.company.api;

public class TweetResponse {

    private String content;

    public TweetResponse() {
    }

    public TweetResponse(String content) {
        this.content = content;
    }

    public String getContent(){
        return content;
    }

}

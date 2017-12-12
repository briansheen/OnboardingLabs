package com.company.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

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

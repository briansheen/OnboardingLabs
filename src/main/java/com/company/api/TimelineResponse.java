package com.company.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimelineResponse {

    private String screenName;

    private String content;

    public TimelineResponse() {
    }

    public TimelineResponse(String screenName, String content) {
        this.screenName = screenName;
        this.content = content;
    }

    @JsonProperty
    public String getScreenName() {
        return screenName;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}

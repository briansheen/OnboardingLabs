package com.company.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimelineResponse {

    private long id;

    private String screenName;

    private String content;

    public TimelineResponse() {
    }

    public TimelineResponse(long id, String screenName, String content) {
        this.id = id;
        this.screenName = screenName;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
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

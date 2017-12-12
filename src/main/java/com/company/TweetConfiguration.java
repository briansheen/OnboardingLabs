package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class TweetConfiguration extends Configuration {
    @NotEmpty
    private String tweetResult;

    @JsonProperty
    public String getTweetResult() {
        return tweetResult;
    }

    @JsonProperty
    public void setTweetResult(String tweetResult) {
        this.tweetResult = tweetResult;
    }
}

package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class TwitterAppConfiguration extends Configuration {

    @NotEmpty
    private String oAuthConsumerKey;

    @NotEmpty
    private String oAuthConsumerSecret;

    @NotEmpty
    private String oAuthAccessToken;

    @NotEmpty
    private String oAuthAccessTokenSecret;

    @JsonProperty
    public String getoAuthConsumerKey() {
        return oAuthConsumerKey;
    }

    @JsonProperty
    public void setoAuthConsumerKey(String oAuthConsumerKey) {
        this.oAuthConsumerKey = oAuthConsumerKey;
    }

    @JsonProperty
    public String getoAuthConsumerSecret() {
        return oAuthConsumerSecret;
    }

    @JsonProperty
    public void setoAuthConsumerSecret(String oAuthConsumerSecret) {
        this.oAuthConsumerSecret = oAuthConsumerSecret;
    }

    @JsonProperty
    public String getoAuthAccessToken() {
        return oAuthAccessToken;
    }

    @JsonProperty
    public void setoAuthAccessToken(String oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    @JsonProperty
    public String getoAuthAccessTokenSecret() {
        return oAuthAccessTokenSecret;
    }

    @JsonProperty
    public void setoAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
        this.oAuthAccessTokenSecret = oAuthAccessTokenSecret;
    }
}

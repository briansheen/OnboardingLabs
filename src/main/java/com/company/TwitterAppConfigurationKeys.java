package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterAppConfigurationKeys {

    private String oAuthConsumerKey;
    private String oAuthConsumerSecret;
    private String oAuthAccessToken;
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

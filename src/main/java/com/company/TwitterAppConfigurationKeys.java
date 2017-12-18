package com.company;

import io.dropwizard.validation.ValidationMethod;
import org.apache.commons.lang3.StringUtils;

public class TwitterAppConfigurationKeys {

    private String oAuthConsumerKey;
    private String oAuthConsumerSecret;
    private String oAuthAccessToken;
    private String oAuthAccessTokenSecret;

    public String getoAuthConsumerKey() {
        return oAuthConsumerKey;
    }

    public void setoAuthConsumerKey(String oAuthConsumerKey) {
        this.oAuthConsumerKey = oAuthConsumerKey;
    }

    public String getoAuthConsumerSecret() {
        return oAuthConsumerSecret;
    }

    public void setoAuthConsumerSecret(String oAuthConsumerSecret) {
        this.oAuthConsumerSecret = oAuthConsumerSecret;
    }

    public String getoAuthAccessToken() {
        return oAuthAccessToken;
    }

    public void setoAuthAccessToken(String oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    public String getoAuthAccessTokenSecret() {
        return oAuthAccessTokenSecret;
    }

    public void setoAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
        this.oAuthAccessTokenSecret = oAuthAccessTokenSecret;
    }

    @ValidationMethod(message="Twitter App Keys cannot be null or empty")
    public boolean isNotNullOrEmpty(){
        return !StringUtils.isAnyBlank(oAuthAccessToken,oAuthAccessTokenSecret,oAuthConsumerKey,oAuthConsumerSecret);
    }
}

package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class TwitterAppConfiguration extends Configuration {

    private TwitterAppConfigurationKeys twitterKeys;

    @JsonProperty
    public TwitterAppConfigurationKeys getTwitterKeys() {
        return twitterKeys;
    }

    @JsonProperty
    public void setTwitterKeys(TwitterAppConfigurationKeys twitterKeys) {
        this.twitterKeys = twitterKeys;
    }
}

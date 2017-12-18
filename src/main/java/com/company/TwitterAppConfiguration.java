package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;

public class TwitterAppConfiguration extends Configuration {

    @Valid
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

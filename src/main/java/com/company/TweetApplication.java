package com.company;

import com.company.resources.TweetResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TweetApplication extends Application<TwitterAppConfiguration> {
    public static void main(String[] args) throws Exception {
        new TweetApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TwitterAppConfiguration> bootstrap) {
        // nothing to do yet
    }


    @Override
    public void run(TwitterAppConfiguration configuration, Environment environment) {
        final TweetResources resource = new TweetResources(configuration.getoAuthConsumerKey(),
                configuration.getoAuthConsumerSecret(),
                configuration.getoAuthAccessToken(),
                configuration.getoAuthAccessTokenSecret());
        environment.jersey().register(resource);
    }
}

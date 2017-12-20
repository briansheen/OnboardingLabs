package com.company;

import com.company.resources.TwitterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TwitterApplication extends Application<TwitterAppConfiguration> {
    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TwitterAppConfiguration> bootstrap) {
        // nothing to do yet
    }


    @Override
    public void run(TwitterAppConfiguration configuration, Environment environment) {
        final TwitterResource resource = new TwitterResource(configuration);
        environment.jersey().register(resource);
    }
}

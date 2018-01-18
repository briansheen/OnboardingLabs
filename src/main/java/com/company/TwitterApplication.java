package com.company;

import com.company.DaggerTwitterComponent;
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
        TwitterComponent twitterComponent = DaggerTwitterComponent.builder()
                .twitterModule(new TwitterModule(configuration))
                .build();
        final TwitterResource resource = twitterComponent.getTwitterResource();
        environment.jersey().register(resource);
    }
}

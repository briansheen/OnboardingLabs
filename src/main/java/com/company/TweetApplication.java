package com.company;

import com.company.resources.TweetResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TweetApplication extends Application<TweetConfiguration> {
    public static void main(String[] args) throws Exception {
        new TweetApplication().run(args);
    }

    @Override
    public String getName() {
        return "post-tweet";
    }

    @Override
    public void initialize(Bootstrap<TweetConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(TweetConfiguration configuration, Environment environment) {
        final TweetResources resource = new TweetResources(configuration.getTweetResult());
        environment.jersey().register(resource);
    }
}

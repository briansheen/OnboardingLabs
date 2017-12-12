package com.company;

import com.company.resources.TweetResources;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TweetApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new TweetApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new TweetResources());
    }
}

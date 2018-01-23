package com.company;

import com.company.DaggerTwitterComponent;
import com.company.resources.TwitterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        TwitterComponent twitterComponent = DaggerTwitterComponent.builder()
                .twitterModule(new TwitterModule(configuration))
                .build();
        final TwitterResource resource = twitterComponent.getTwitterResource();
        environment.jersey().register(resource);
    }
}

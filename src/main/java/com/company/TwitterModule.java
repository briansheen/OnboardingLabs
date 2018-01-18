package com.company;

import com.company.resources.TwitterResource;
import com.company.services.TwitterService;
import dagger.Module;
import dagger.Provides;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Module
public class TwitterModule {

    private static TwitterAppConfiguration configuration;

    public TwitterModule(TwitterAppConfiguration configuration) {
        this.configuration = configuration;
    }

    @Provides static Twitter provideTwitter(){
        TwitterAppConfigurationKeys keys = configuration.getTwitterKeys();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(keys.getoAuthConsumerKey())
                .setOAuthConsumerSecret(keys.getoAuthConsumerSecret())
                .setOAuthAccessToken(keys.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(keys.getoAuthAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    @Provides static TwitterService provideTwitterService(){
        TwitterService twitterService = new TwitterService(TwitterModule.provideTwitter());
        return twitterService;
    }
}

package com.company;

import com.company.api.TwitterErrorResponse;
import com.company.api.TweetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;

public class Tweet {

    private static Logger LOGGER = LoggerFactory.getLogger(Tweet.class);

    public Response run(String message, TwitterAppConfigurationKeys keys) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(keys.getoAuthConsumerKey())
                .setOAuthConsumerSecret(keys.getoAuthConsumerSecret())
                .setOAuthAccessToken(keys.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(keys.getoAuthAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            Status status = twitter.updateStatus(message);
            return Response.ok(new TweetResponse(status.getText())).build();
        } catch (TwitterException e) {
            LOGGER.error("There was an error interacting with the Twitter API and/or Twitter Keys", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to post your tweet.")).build();
        }
    }
}

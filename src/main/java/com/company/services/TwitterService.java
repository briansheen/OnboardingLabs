package com.company.services;


import com.company.TwitterAppConfigurationKeys;
import com.company.api.TweetResponse;
import com.company.api.TwitterErrorResponse;
import com.company.models.TwitterPost;
import com.company.models.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.ResponseList;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterService {
    private static Logger logger = LoggerFactory.getLogger(TwitterService.class);
    private static TwitterService instance = null;

    private TwitterService() {
    }

    public static TwitterService getInstance() {
        if (instance == null) {
            instance = new TwitterService();
        }
        return instance;
    }

    public Response postTweet(String message, TwitterAppConfigurationKeys keys) {
        if (StringUtils.isAllBlank(message) || message.length() > 280) {
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "Form Parameter 'message' cannot be null, empty white spaces, or longer than 280 characters.")).build();
        }
        Twitter twitter = buildTwitter(keys);
        try {
            Status status = twitter.updateStatus(message);
            return Response.ok(new TweetResponse(status.getText())).build();
        } catch (TwitterException e) {
            logger.error("In postTweet: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to post your tweet.")).build();
        }
    }

    public Response getTimeline(TwitterAppConfigurationKeys keys) {
        Twitter twitter = buildTwitter(keys);
        try {
            return Response.ok(buildTimelineList(twitter.getHomeTimeline())).build();
        } catch (TwitterException e) {
            logger.error("In getTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to get your twitter timeline.")).build();
        }
    }

    public Response getFilteredTimeline(String filter, TwitterAppConfigurationKeys keys) {
        if(StringUtils.isAllBlank(filter)){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "Query Parameter 'filter' cannot be null or empty white spaces.")).build();
        }
        Twitter twitter = buildTwitter(keys);
        try {
            return Response.ok(buildTimelineList(twitter.getHomeTimeline()).stream()
                    .filter(tweet -> StringUtils.containsIgnoreCase(tweet.getMessage(), filter))
                    .collect(Collectors.toList())).build();
        } catch (TwitterException e) {
            logger.error("In getFilteredTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to get your filtered twitter timeline.")).build();
        }
    }

    private Twitter buildTwitter(TwitterAppConfigurationKeys keys) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(keys.getoAuthConsumerKey())
                .setOAuthConsumerSecret(keys.getoAuthConsumerSecret())
                .setOAuthAccessToken(keys.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(keys.getoAuthAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    private List<TwitterPost> buildTimelineList(ResponseList<Status> homeTimeline) {
        return homeTimeline.stream()
                .map(status -> new TwitterPost(new User(status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL()), status.getText(), status.getCreatedAt()))
                .collect(Collectors.toList());
    }
}

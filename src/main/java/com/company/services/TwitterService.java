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
import java.util.ArrayList;
import java.util.List;

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
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "Message parameter cannot be null, empty white spaces, or longer than 280 characters.")).build();
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
            ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
            List<TwitterPost> timelineResponses = new ArrayList<>();
            for (Status s : homeTimeline) {
                User user = new User();
                user.setName(s.getUser().getName());
                user.setProfileImageUrl(s.getUser().getProfileImageURL());
                user.setTwitterHandle(s.getUser().getScreenName());
                TwitterPost twitterPost = new TwitterPost();
                twitterPost.setUser(user);
                twitterPost.setMessage(s.getText());
                twitterPost.setCreatedAt(s.getCreatedAt());
                timelineResponses.add(twitterPost);
            }
            return Response.ok(timelineResponses).build();
        } catch (TwitterException e) {
            logger.error("In getTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to get your twitter timeline.")).build();
        }
    }

    private Twitter buildTwitter(TwitterAppConfigurationKeys keys){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(keys.getoAuthConsumerKey())
                .setOAuthConsumerSecret(keys.getoAuthConsumerSecret())
                .setOAuthAccessToken(keys.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(keys.getoAuthAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}

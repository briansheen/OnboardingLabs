package com.company.services;


import com.company.TwitterAppConfigurationKeys;
import com.company.models.TwitterPost;
import com.company.models.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;

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

    public TwitterPost postTweet(String message, TwitterAppConfigurationKeys keys) {
        Twitter twitter = buildTwitter(keys);
        if (StringUtils.isAllBlank(message) || message.length() > 280) {
            logger.error("Form Parameter 'message' cannot be null, empty white spaces, or longer than 280 characters.");
        }
        try {
            Status status = twitter.updateStatus(message);
            return new TwitterPost(new User(status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL()), status.getText(), status.getCreatedAt());
        } catch (TwitterException e) {
            logger.error("In postTweet: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return null;
        }
    }

    public List<TwitterPost> getTimeline(TwitterAppConfigurationKeys keys) {
        Twitter twitter = buildTwitter(keys);
        try {
            return twitter.getHomeTimeline().stream()
                    .map(status -> new TwitterPost(new User(status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL()), status.getText(), status.getCreatedAt()))
                    .collect(Collectors.toList());
        } catch (TwitterException e) {
            logger.error("In getTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return null;
        }
    }

    public List<TwitterPost> getFilteredTimeline(String filter, TwitterAppConfigurationKeys keys) {
        Twitter twitter = buildTwitter(keys);
        try {
            return twitter.getHomeTimeline().stream()
                    .filter(status -> StringUtils.containsIgnoreCase(status.getText(), filter))
                    .map(status -> new TwitterPost(new User(status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL()), status.getText(), status.getCreatedAt()))
                    .collect(Collectors.toList());
        } catch (TwitterException e) {
            logger.error("In getFilteredTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            return null;
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
}

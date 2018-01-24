package com.company.services;


import com.company.models.TwitterPost;
import com.company.models.TwitterUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwitterService {
    private static Logger logger = LoggerFactory.getLogger(TwitterService.class);
    private Twitter twitter;

    @Inject
    public TwitterService(Twitter twitter) {
        this.twitter = twitter;
    }

    public TwitterPost postTweet(String message) throws TwitterException {
        if (StringUtils.isAllBlank(message) || message.length() > 280) {
            logger.error("Form Parameter 'message' cannot be null, empty white spaces, or longer than 280 characters.");
            throw new TwitterException("Tweet cannot be null, empty white spaces, or longer than 280 characters.");
        }
        try {
            Status status = twitter.updateStatus(message);
            return Stream.of(status).map(s -> new TwitterPost(new TwitterUser(s.getUser().getScreenName(), s.getUser().getName(), s.getUser().getProfileImageURL()), s.getText(), s.getCreatedAt(), String.valueOf(s.getId()))).collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            logger.error("In postTweet: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            throw new TwitterException("There was an error interacting with the Twitter API and/or Twitter Keys.");
        }
    }

    public List<TwitterPost> getTimeline() throws TwitterException {
        try {
            return twitter.getHomeTimeline().stream()
                    .map(status -> new TwitterPost(new TwitterUser(status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL()), status.getText(), status.getCreatedAt(), String.valueOf(status.getId())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("In getTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            throw new TwitterException("There was an error interacting with the Twitter API and/or Twitter Keys.");
        }
    }

    public List<TwitterPost> getFilteredTimeline(String filter) throws TwitterException {
        if (StringUtils.isAllBlank(filter)) {
            logger.error("Query Parameter 'filter' cannot be null or empty white spaces.");
            throw new TwitterException("Filter parameter cannot be null or empty white spaces.");
        }
        try {
            return twitter.getHomeTimeline().stream()
                    .filter(status -> StringUtils.containsIgnoreCase(status.getText(), filter))
                    .map(status -> new TwitterPost(new TwitterUser(status.getUser().getScreenName(), status.getUser().getName(), status.getUser().getProfileImageURL()), status.getText(), status.getCreatedAt(), String.valueOf(status.getId())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("In getFilteredTimeline: There was an error interacting with the Twitter API and/or Twitter Keys.", e);
            throw new TwitterException("There was an error interacting with the Twitter API and/or Twitter Keys.");
        }
    }
}

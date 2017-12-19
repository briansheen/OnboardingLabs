package com.company;

import com.company.api.TimelineResponse;
import com.company.api.TwitterErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.ResponseList;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class GetTimeline {

    private static Logger logger = LoggerFactory.getLogger(GetTimeline.class);

    public Response run(TwitterAppConfigurationKeys keys) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(keys.getoAuthConsumerKey())
                .setOAuthConsumerSecret(keys.getoAuthConsumerSecret())
                .setOAuthAccessToken(keys.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(keys.getoAuthAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
            List<TimelineResponse> timelineResponses = new ArrayList<>();
            for (Status s : homeTimeline) {
                timelineResponses.add(new TimelineResponse(s.getUser().getScreenName(), s.getText()));
            }
            return Response.ok(timelineResponses).build();
        } catch (TwitterException e) {
            logger.error("There was an error interacting with the Twitter API and/or Twitter Keys", e);
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to get your twitter timeline.")).build();
        }
    }
}

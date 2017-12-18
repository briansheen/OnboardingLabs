package com.company;

import com.company.api.TimelineResponse;
import com.company.api.TwitterErrorResponse;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class GetTimeline {
    public Response run(TwitterAppConfigurationKeys keys) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(keys.getoAuthConsumerKey())
                .setOAuthConsumerSecret(keys.getoAuthConsumerSecret())
                .setOAuthAccessToken(keys.getoAuthAccessToken())
                .setOAuthAccessTokenSecret(keys.getoAuthAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try{
            ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
            List<TimelineResponse> timelineResponses = new ArrayList<>();
            for(Status s : homeTimeline){
                timelineResponses.add(new TimelineResponse(s.getUser().getScreenName(),s.getText()));
            }
            return Response.ok(timelineResponses).build();
        } catch(TwitterException e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(),"There was an error when trying to get your twitter timeline.")).build();
        }
    }
}

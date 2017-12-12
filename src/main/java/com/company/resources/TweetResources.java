package com.company.resources;


import com.codahale.metrics.annotation.Timed;
import com.company.GetTimeline;
import com.company.Tweet;
import com.company.api.TimelineResponse;
import com.company.api.TweetResponse;
import twitter4j.ResponseList;
import twitter4j.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetResources {
    private final AtomicLong counter;

    public TweetResources() {
        this.counter = new AtomicLong();
    }

    @POST
    @Path("/tweet")
    public TweetResponse addTweet(@FormParam("message") String message) {
        String tweetResult = Tweet.run(message);
        return new TweetResponse(counter.incrementAndGet(),tweetResult);
    }

    @GET
    @Path("/timeline")
    public List<TimelineResponse> getTimeline() {
        List<TimelineResponse> homeTimeLine = new ArrayList<>();
        ResponseList<Status> timeline = GetTimeline.run();
        if(timeline!=null) {
            for (Status s : timeline) {
                homeTimeLine.add(new TimelineResponse(counter.incrementAndGet(), s.getUser().getScreenName(), s.getText()));
            }
        }
        return homeTimeLine;
    }
}

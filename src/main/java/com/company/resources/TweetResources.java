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
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetResources {

    public TweetResources() {
    }

    @POST
    @Path("/tweet")
    public Response addTweet(@FormParam("message") String message) {
        Tweet tweet = new Tweet();
        return tweet.run(message);
    }

    @GET
    @Path("/timeline")
    public Response getTimeline() {
        GetTimeline getTimeline = new GetTimeline();
        return getTimeline.run();
    }
}

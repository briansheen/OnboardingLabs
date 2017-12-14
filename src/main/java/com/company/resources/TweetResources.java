package com.company.resources;

import com.company.GetTimeline;
import com.company.Tweet;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetResources {

    public TweetResources() {
    }

    @POST
    @Path("/tweet")
    public Response addTweet(@FormParam("message") @NotNull String message) {
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

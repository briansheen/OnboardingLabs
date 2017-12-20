package com.company.resources;


import com.company.TwitterAppConfiguration;
import com.company.services.TwitterService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TweetResources {
    private final TwitterAppConfiguration configuration;
    private TwitterService twitterService = TwitterService.getInstance();

    public TweetResources(TwitterAppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Path("/tweet")
    public Response addTweet(@FormParam("message") String message) {
        return twitterService.postTweet(message, configuration.getTwitterKeys());
    }

    @GET
    @Path("/timeline")
    public Response getTimeline() {
        return twitterService.getTimeline(configuration.getTwitterKeys());
    }
}

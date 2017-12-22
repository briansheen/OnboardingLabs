package com.company.resources;


import com.company.TwitterAppConfiguration;
import com.company.services.TwitterService;
import io.dropwizard.validation.ValidationMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {
    private final TwitterAppConfiguration configuration;
    private TwitterService twitterService = TwitterService.getInstance();

    public TwitterResource(TwitterAppConfiguration configuration) {
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

    @GET
    @Path("/filter")
    public Response getFilteredTimeline(@QueryParam("filter") String filter){
        return twitterService.getFilteredTimeline(filter, configuration.getTwitterKeys());
    }
}

package com.company.resources;


import com.company.TwitterAppConfiguration;
import com.company.api.TwitterErrorResponse;
import com.company.models.TwitterPost;
import com.company.services.TwitterService;
import org.apache.commons.lang3.StringUtils;
import twitter4j.TwitterException;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {
    private final TwitterAppConfiguration configuration;
    private TwitterService twitterService = TwitterService.getInstance();

    public TwitterResource(TwitterAppConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @POST
    @Path("/tweet")
    public Response addTweet(@FormParam("message") String message) {
        try {
            TwitterPost twitterPost = twitterService.postTweet(message, configuration.getTwitterKeys());
            return Response.ok(twitterPost).build();
        }
        catch (TwitterException e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    @GET
    @Path("/timeline")
    public Response getTimeline() {
        try{
            List<TwitterPost> homeTimeline = twitterService.getTimeline(configuration.getTwitterKeys());
            return Response.ok(homeTimeline).build();
        }
        catch(TwitterException e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    @GET
    @Path("/filter")
    public Response getFilteredTimeline(@QueryParam("filter") String filter){
        try {
            List<TwitterPost> filteredTimeline = twitterService.getFilteredTimeline(filter, configuration.getTwitterKeys());
            return Response.ok(filteredTimeline).build();
        }
        catch (TwitterException e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }
}

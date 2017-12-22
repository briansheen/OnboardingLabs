package com.company.resources;


import com.company.TwitterAppConfiguration;
import com.company.api.TwitterErrorResponse;
import com.company.models.TwitterPost;
import com.company.services.TwitterService;
import org.apache.commons.lang3.StringUtils;


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

    @POST
    @Path("/tweet")
    public Response addTweet(@FormParam("message") String message) {
        if (StringUtils.isAllBlank(message) || message.length() > 280) {
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "Form Parameter 'message' cannot be null, empty white spaces, or longer than 280 characters.")).build();
        }
        TwitterPost twitterPost = twitterService.postTweet(message, configuration.getTwitterKeys());
        if(twitterPost!=null){
            return Response.ok(twitterPost).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to post your tweet.")).build();
    }

    @GET
    @Path("/timeline")
    public Response getTimeline() {
        List<TwitterPost> homeTimeline = twitterService.getTimeline(configuration.getTwitterKeys());
        if(homeTimeline!=null){
            return Response.ok(homeTimeline).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to get your twitter timeline.")).build();
    }

    @GET
    @Path("/filter")
    public Response getFilteredTimeline(@QueryParam("filter") String filter){
        if(StringUtils.isAllBlank(filter)){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "Query Parameter 'filter' cannot be null or empty white spaces.")).build();
        }
        List<TwitterPost> filteredTimeline = twitterService.getFilteredTimeline(filter, configuration.getTwitterKeys());
        if(filteredTimeline!=null){
            return Response.ok(filteredTimeline).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "There was an error when trying to get your filtered twitter timeline.")).build();
    }
}

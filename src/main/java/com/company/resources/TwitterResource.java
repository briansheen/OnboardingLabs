package com.company.resources;


import com.company.api.TwitterErrorResponse;
import com.company.models.PostTweetRequest;
import com.company.models.PostReplyRequest;
import com.company.models.TwitterPost;
import com.company.services.TwitterService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/1.0/twitter")
@Produces(MediaType.APPLICATION_JSON)
public class TwitterResource {
    private TwitterService twitterService;

    @Inject
    public TwitterResource(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @POST
    @Path("/tweet")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTweet(PostTweetRequest message) {
        try {
            TwitterPost twitterPost = twitterService.postTweet(message.getMessage());
            return okResponse(twitterPost);
        }
        catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    @POST
    @Path("/reply")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replyToTweet(PostReplyRequest reply) {
        try {
            TwitterPost twitterPost = twitterService.replyToTweet(reply.getReplyMessage(), reply.getInReplyToStatusId());
            return okResponse(twitterPost);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    @GET
    @Path("/timeline")
    public Response getTimeline() {
        try{
            List<TwitterPost> homeTimeline = twitterService.getTimeline();
            return okResponse(homeTimeline);
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    @GET
    @Path("/filter")
    public Response getFilteredTimeline(@QueryParam("filter") String filter){
        try {
            List<TwitterPost> filteredTimeline = twitterService.getFilteredTimeline(filter);
            return okResponse(filteredTimeline);
        }
        catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    @GET
    @Path("/mytweets")
    public Response getMyTweets(){
        try{
            List<TwitterPost> myTweets = twitterService.getMyTweets();
            return okResponse(myTweets);
        } catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage())).build();
        }
    }

    private Response okResponse(Object o){
        return Response.ok(o).build();
    }
}

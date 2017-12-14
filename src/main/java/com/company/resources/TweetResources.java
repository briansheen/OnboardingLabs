package com.company.resources;

import com.company.GetTimeline;
import com.company.Tweet;
import com.company.api.TwitterErrorResponse;
import org.apache.commons.lang3.StringUtils;

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
    public Response addTweet(@FormParam("message") String message) {
        if(StringUtils.isAllBlank(message) || message.length()>280){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TwitterErrorResponse(Response.Status.NOT_ACCEPTABLE.getStatusCode(),"Message parameter cannot be null, empty white spaces, or longer than 280 characters.")).build();
        }
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

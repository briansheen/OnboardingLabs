package com.company;

import com.company.api.TweetResponse;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;

public class Tweet {
    public Response run(String args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jA80AzMmxvpcZCwbuqsWEirMs")
                .setOAuthConsumerSecret("dqRNGieSAqYi84jFWHRD9Budacl7iAB3wHc8rklCkwIaR1dOaz")
                .setOAuthAccessToken("22681954-VXEVuclUJ1ztDeSRBgkTdystVjtzaZOSLbw0a4G1r")
                .setOAuthAccessTokenSecret("JgGlTVvDmaWRSVYpt4YgzpejmfVmjyWvNnwM8flCP183j");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try{
            if(args == null || args.length()==0 || args.length()>280){
                throw new IndexOutOfBoundsException();
            }
            Status status = twitter.updateStatus(args);
            return Response.ok(new TweetResponse(status.getText())).build();
        } catch (IndexOutOfBoundsException e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TweetResponse("Tweet must be 1-280 characters!")).build();
        } catch (TwitterException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

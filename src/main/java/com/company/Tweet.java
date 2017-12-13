package com.company;

import com.company.api.TwitterErrorResponse;
import com.company.api.TweetResponse;
import org.apache.commons.lang3.StringUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import javax.ws.rs.core.Response;

public class Tweet {
    public Response run(String message) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jA80AzMmxvpcZCwbuqsWEirMs")
                .setOAuthConsumerSecret("dqRNGieSAqYi84jFWHRD9Budacl7iAB3wHc8rklCkwIaR1dOaz")
                .setOAuthAccessToken("22681954-VXEVuclUJ1ztDeSRBgkTdystVjtzaZOSLbw0a4G1r")
                .setOAuthAccessTokenSecret("JgGlTVvDmaWRSVYpt4YgzpejmfVmjyWvNnwM8flCP183j");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            if (StringUtils.isAllBlank(message) || message.length() > 280) {
                return Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(404, "Tweet message cannot be blank spaces or longer than 280 characters")).build();
            }
            Status status = twitter.updateStatus(message);
            return Response.ok(new TweetResponse(status.getText())).build();
        } catch (TwitterException e) {
            return Response.status(e.getStatusCode()).entity(new TwitterErrorResponse(e.getStatusCode(), e.getMessage())).build();
        }
    }
}

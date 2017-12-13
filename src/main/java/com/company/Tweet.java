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
            Status status = twitter.updateStatus(message);
            return Response.ok(new TweetResponse(status.getText())).build();
        } catch (TwitterException e) {
            return Response.status(e.getStatusCode()).entity(new TwitterErrorResponse(e.getStatusCode(), e.getMessage())).build();
        }
    }
}

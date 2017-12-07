package com.company;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class GetTimeline {

    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("jA80AzMmxvpcZCwbuqsWEirMs")
                .setOAuthConsumerSecret("dqRNGieSAqYi84jFWHRD9Budacl7iAB3wHc8rklCkwIaR1dOaz")
                .setOAuthAccessToken("22681954-VXEVuclUJ1ztDeSRBgkTdystVjtzaZOSLbw0a4G1r")
                .setOAuthAccessTokenSecret("JgGlTVvDmaWRSVYpt4YgzpejmfVmjyWvNnwM8flCP183j");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try{
            ResponseList<Status> homeTimeLine = twitter.getHomeTimeline();
            for(Status s : homeTimeLine){
                System.out.println("@"+s.getUser().getScreenName());
                System.out.println(s.getText());
            }
        } catch(TwitterException e){
            System.out.println("error getting twitter timeline");
        }
    }
}

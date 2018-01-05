package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TwitterAppConfigurationKeysTest {

    private TwitterAppConfigurationKeys twitterAppConfigurationKeys;

    @Before
    public void setup(){
        twitterAppConfigurationKeys = new TwitterAppConfigurationKeys();
    }

    @Test
    public void testKeySettersAndGetters(){
        String oAuthConsumerKey = "consumer key";
        String oAuthConsumerSecret = "consumer secret";
        String oAuthAccessToken = "access token";
        String oAuthAccessTokenSecret = "token secret";

        twitterAppConfigurationKeys.setoAuthAccessTokenSecret(oAuthAccessTokenSecret);
        assertEquals(oAuthAccessTokenSecret, twitterAppConfigurationKeys.getoAuthAccessTokenSecret());
        assertFalse(twitterAppConfigurationKeys.isNotNullOrEmpty());

        twitterAppConfigurationKeys.setoAuthAccessToken(oAuthAccessToken);
        assertEquals(oAuthAccessToken, twitterAppConfigurationKeys.getoAuthAccessToken());
        assertFalse(twitterAppConfigurationKeys.isNotNullOrEmpty());

        twitterAppConfigurationKeys.setoAuthConsumerKey(oAuthConsumerKey);
        assertEquals(oAuthConsumerKey, twitterAppConfigurationKeys.getoAuthConsumerKey());
        assertFalse(twitterAppConfigurationKeys.isNotNullOrEmpty());

        twitterAppConfigurationKeys.setoAuthConsumerSecret(oAuthConsumerSecret);
        assertEquals(oAuthConsumerSecret, twitterAppConfigurationKeys.getoAuthConsumerSecret());
        assertTrue(twitterAppConfigurationKeys.isNotNullOrEmpty());
    }



}

package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwitterAppConfigurationTest {
    private TwitterAppConfiguration twitterAppConfiguration;

    @Before
    public void setup(){
        twitterAppConfiguration = new TwitterAppConfiguration();
    }

    @Test
    public void testConfigurationGettersAndSetters(){
        TwitterAppConfigurationKeys keys = new TwitterAppConfigurationKeys();
        twitterAppConfiguration.setTwitterKeys(keys);

        assertEquals(keys, twitterAppConfiguration.getTwitterKeys());
    }
}

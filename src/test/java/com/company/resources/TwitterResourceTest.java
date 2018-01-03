package com.company.resources;

import com.company.TwitterAppConfiguration;
import com.company.TwitterAppConfigurationKeys;
import com.company.models.TwitterPost;
import com.company.models.TwitterUser;
import com.company.services.TwitterService;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Twitter;
import twitter4j.TwitterException;


import javax.ws.rs.core.Response;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterResourceTest {
    private TwitterService twitterServiceMock;
    private TwitterAppConfiguration configurationMock;
    private TwitterResource twitterResource;
    private TwitterAppConfigurationKeys mockKeys = new TwitterAppConfigurationKeys();

    @Before
    public void setup() {
        twitterServiceMock = mock(TwitterService.class);
        configurationMock = mock(TwitterAppConfiguration.class);
        twitterResource = new TwitterResource(configurationMock);
        twitterResource.setTwitterService(twitterServiceMock);
    }

    @Test
    public void addTweetGood() throws TwitterException {
        TwitterPost twitterPost = getTwitterPost("good post message");
        String val = "a good message";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenReturn(twitterPost);
        assertEquals(Response.ok(twitterPost).build(), twitterResource.addTweet(val));


    }

    private TwitterPost getTwitterPost(String text) {
        return new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), text, new Date(1514908981));
    }

}

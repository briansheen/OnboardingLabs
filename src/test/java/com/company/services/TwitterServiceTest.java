package com.company.services;

import com.company.models.*;
import com.company.TwitterAppConfigurationKeys;
import net.bytebuddy.utility.RandomString;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import javax.xml.ws.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterServiceTest {

    private Twitter twitterMock;
    private TwitterAppConfigurationKeys mockKeys;

    @Before
    public void setup() {
        twitterMock = mock(Twitter.class);
        mockKeys = new TwitterAppConfigurationKeys();
    }

    @Test
    public void tweetGood() throws TwitterException {
        when(twitterMock.updateStatus(anyString())).thenReturn(getFixtureStatus());
        TwitterService twitterService = TwitterService.getInstance();
        twitterService.setTwitter(twitterMock);
        TwitterPost expected = new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), "I am a fixture status", new Date(1514908981));
        assertEquals(expected, twitterService.postTweet("this is a good string!", mockKeys));
    }

    @Test
    public void tweetTooLong() throws TwitterException {
        String val = RandomString.make(281);
        when(twitterMock.updateStatus(val)).thenThrow(new TwitterException("must be less than 280 chars"));
        TwitterService twitterService = TwitterService.getInstance();
        twitterService.setTwitter(twitterMock);
        assertNull(twitterService.postTweet(val, mockKeys));
    }

    @Test
    public void tweetIsBlank() throws TwitterException {
        String val = "";
        when(twitterMock.updateStatus(val)).thenThrow(new TwitterException("cannot be null"));
        TwitterService twitterService = TwitterService.getInstance();
        twitterService.setTwitter(twitterMock);
        assertNull(twitterService.postTweet(val, mockKeys));
    }

    @Test
    public void tweetIsNull() throws TwitterException {
        String val = null;
        when(twitterMock.updateStatus(val)).thenThrow(new TwitterException("cannot be null"));
        TwitterService twitterService = TwitterService.getInstance();
        twitterService.setTwitter(twitterMock);
        assertNull(twitterService.postTweet(val, mockKeys));
    }

    @Test
    public void timelineGood() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus());
        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);
        TwitterService twitterService = TwitterService.getInstance();
        twitterService.setTwitter(twitterMock);
        List<TwitterPost> expected = new ArrayList<>();
        expected.add(new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), "I am a fixture status", new Date(1514908981)));
        assertEquals(expected, twitterService.getTimeline(mockKeys));
    }



    private Status getFixtureStatus() {
        FixtureStatus fixtureStatus = new FixtureStatus();
        fixtureStatus.setCreatedAt(new Date(1514908981));
        fixtureStatus.setText("I am a fixture status");
        fixtureStatus.setUser(getFixtureUser());
        return fixtureStatus;
    }

    private User getFixtureUser() {
        FixtureUser fixtureUser = new FixtureUser();
        fixtureUser.setName("Lab Nine");
        fixtureUser.setScreenName("Lab_9");
        fixtureUser.setProfileImageURL("https://confluence.dev.lithium.com/x/8C5EBQ");
        return fixtureUser;
    }
}

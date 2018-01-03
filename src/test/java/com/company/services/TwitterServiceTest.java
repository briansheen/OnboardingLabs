package com.company.services;

import com.company.models.*;
import com.company.TwitterAppConfigurationKeys;
import net.bytebuddy.utility.RandomString;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterServiceTest {

    private Twitter twitterMock;
    private TwitterAppConfigurationKeys mockKeys;
    private TwitterService twitterService;

    @Before
    public void setup() {
        twitterMock = mock(Twitter.class);
        mockKeys = new TwitterAppConfigurationKeys();
        twitterService = TwitterService.getInstance();
        twitterService.setTwitter(twitterMock);
    }

    @Test
    public void tweetGood() throws TwitterException {
        when(twitterMock.updateStatus(anyString())).thenReturn(getFixtureStatus("I am a fixture status"));
        TwitterPost expected = getExpectedTwitterPost("I am a fixture status");
        assertEquals(expected, twitterService.postTweet("this is a good string!", mockKeys));
    }

    @Test(expected = TwitterException.class)
    public void tweetTooLong() throws TwitterException {
        String val = RandomString.make(281);
        when(twitterMock.updateStatus(val)).thenThrow(new TwitterException("must be less than 280 chars"));
        twitterService.postTweet(val, mockKeys);
    }

    @Test(expected = TwitterException.class)
    public void tweetIsBlank() throws TwitterException {
        String val = "";
        when(twitterMock.updateStatus(val)).thenThrow(new TwitterException("cannot be null"));
        twitterService.postTweet(val, mockKeys);
    }

    @Test(expected = TwitterException.class)
    public void tweetIsNull() throws TwitterException {
        String val = null;
        when(twitterMock.updateStatus(val)).thenThrow(new TwitterException("cannot be null"));
        twitterService.postTweet(val, mockKeys);
    }

    @Test
    public void timelineGood() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("I am a fixture status"));
        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);
        List<TwitterPost> expected = new ArrayList<>();
        expected.add(getExpectedTwitterPost("I am a fixture status"));
        assertEquals(expected, twitterService.getTimeline(mockKeys));
    }

    @Test
    public void filteredTimelineGood() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("Hello is in this one"));
        fixtureResponseList.add(getFixtureStatus("But not in this one"));
        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);
        List<TwitterPost> expected = new ArrayList<>();
        expected.add(getExpectedTwitterPost("Hello is in this one"));
        assertEquals(expected, twitterService.getFilteredTimeline("hello", mockKeys));
        expected.add(getExpectedTwitterPost("But not in this one"));
        assertEquals(expected, twitterService.getFilteredTimeline("one", mockKeys));
    }

    @Test(expected = TwitterException.class)
    public void filterIsBlank() throws TwitterException {
        String val = "";
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("I am a fixture status"));
        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);
        twitterService.getFilteredTimeline(val, mockKeys);
    }

    @Test(expected = TwitterException.class)
    public void filterIsNull() throws TwitterException {
        String val = null;
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("I am a fixture status"));
        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);
        twitterService.getFilteredTimeline(val, mockKeys);
    }

    private Status getFixtureStatus(String text) {
        FixtureStatus fixtureStatus = new FixtureStatus();
        fixtureStatus.setCreatedAt(new Date(1514908981));
        fixtureStatus.setText(text);
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

    private TwitterPost getExpectedTwitterPost(String text) {
        return new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), text, new Date(1514908981));
    }
}

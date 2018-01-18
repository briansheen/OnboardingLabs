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
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterServiceTest {

    private Twitter twitterMock;
    private TwitterService twitterService;

    @Before
    public void setup() {
        twitterMock = mock(Twitter.class);
        twitterService = new TwitterService(twitterMock);
    }

    @Test
    public void testNullTwitterObject() throws TwitterException {
        String message = "this shouldn't be posted!";
        String expectedMessage = "There was an error interacting with the Twitter API and/or Twitter Keys.";
        twitterMock = null;
        twitterService = new TwitterService(twitterMock);
        try {
            twitterService.postTweet(message);
        } catch (TwitterException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
        try {
            twitterService.getTimeline();
        } catch (TwitterException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
        try {
            twitterService.getFilteredTimeline("doesn't matter");
        } catch (TwitterException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void testPostTweet() throws TwitterException {
        when(twitterMock.updateStatus(anyString())).thenReturn(getFixtureStatus("I am a fixture status"));
        TwitterPost expected = getExpectedTwitterPost("I am a fixture status");

        assertEquals(expected, twitterService.postTweet("this is a good string!"));
    }

    @Test
    public void testPostTweetTooLong() {
        String val = RandomString.make(281);
        try {
            twitterService.postTweet(val);
            fail("Expected a Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Tweet cannot be null, empty white spaces, or longer than 280 characters.", e.getMessage());
        }
    }

    @Test
    public void testPostTweetBlank() {
        String val = "";
        try {
            twitterService.postTweet(val);
            fail("Expected a Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Tweet cannot be null, empty white spaces, or longer than 280 characters.", e.getMessage());
        }
    }

    @Test
    public void testPostTweetNull() {
        String val = null;
        try {
            twitterService.postTweet(val);
            fail("Expected a Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Tweet cannot be null, empty white spaces, or longer than 280 characters.", e.getMessage());
        }
    }

    @Test
    public void testPostTweetException() throws TwitterException {
        String expectedErrorMessage = "There was an error interacting with the Twitter API and/or Twitter Keys.";
        when(twitterMock.updateStatus(anyString())).thenThrow(new TwitterException("mocking that something with Twitter went wrong"));
        try {
            twitterService.postTweet("Exception should be thrown!");
        } catch (TwitterException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void testGetTimeline() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("I am a fixture status"));

        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);

        List<TwitterPost> expected = new ArrayList<>();
        expected.add(getExpectedTwitterPost("I am a fixture status"));

        assertEquals(expected, twitterService.getTimeline());
    }

    @Test
    public void testGetTimelineException() throws TwitterException {
        String expectedErrorMessage = "There was an error interacting with the Twitter API and/or Twitter Keys.";
        when(twitterMock.getHomeTimeline()).thenThrow(new TwitterException("mocking that something with Twitter went wrong"));
        try {
            twitterService.getTimeline();
        } catch (TwitterException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void testGetFilteredTimeline() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("Hello is in this one"));
        fixtureResponseList.add(getFixtureStatus("But not in this one"));

        when(twitterMock.getHomeTimeline()).thenReturn(fixtureResponseList);

        List<TwitterPost> expected = new ArrayList<>();
        expected.add(getExpectedTwitterPost("Hello is in this one"));

        assertEquals(expected, twitterService.getFilteredTimeline("hello"));

        expected.add(getExpectedTwitterPost("But not in this one"));

        assertEquals(expected, twitterService.getFilteredTimeline("one"));
    }

    @Test
    public void testGetFilteredTimelineBlankFilter() {
        String val = "";
        try {
            twitterService.getFilteredTimeline(val);
            fail("Expected a Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Filter parameter cannot be null or empty white spaces.", e.getMessage());
        }
    }

    @Test
    public void testGetFilteredTimelineNullFilter() {
        String val = null;
        try {
            twitterService.getFilteredTimeline(val);
            fail("Expected a Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Filter parameter cannot be null or empty white spaces.", e.getMessage());
        }
    }

    @Test
    public void testGetFilteredTimelineException() throws TwitterException {
        String expectedErrorMessage = "There was an error interacting with the Twitter API and/or Twitter Keys.";
        when(twitterMock.getHomeTimeline()).thenThrow(new TwitterException("mocking that something with Twitter went wrong"));
        try {
            twitterService.getFilteredTimeline("Exception should be thrown!");
        } catch (TwitterException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
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

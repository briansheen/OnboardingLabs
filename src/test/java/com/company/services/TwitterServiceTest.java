package com.company.services;

import com.company.models.*;
import net.bytebuddy.utility.RandomString;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    public void testGetMyTweets() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();
        fixtureResponseList.add(getFixtureStatus("My first tweet"));
        fixtureResponseList.add(getFixtureStatus("My second tweet"));

        when(twitterMock.getUserTimeline()).thenReturn(fixtureResponseList);

        List<TwitterPost> expected = new ArrayList<>();
        expected.add(getExpectedTwitterPost("My first tweet"));
        expected.add(getExpectedTwitterPost("My second tweet"));

        assertEquals(expected, twitterService.getMyTweets());
    }

    @Test
    public void testGetMyTweetsEmpty() throws TwitterException {
        ResponseList<Status> fixtureResponseList = new FixtureResponseList<>();

        when(twitterMock.getUserTimeline()).thenReturn(fixtureResponseList);

        List<TwitterPost> expected = new ArrayList<>();

        assertEquals(expected, twitterService.getMyTweets());
    }

    @Test
    public void testGetMyTweetsException() throws TwitterException {
        String expectedErrorMessage = "There was an error interacting with the Twitter API and/or Twitter Keys.";
        when(twitterMock.getUserTimeline()).thenThrow(new TwitterException("mocking something with Twitter went wrong"));
        try {
            twitterService.getMyTweets();
        } catch (TwitterException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void testReplyToTweet() throws TwitterException {
        when(twitterMock.updateStatus(any(StatusUpdate.class))).thenReturn(getFixtureStatus("I am a fixture status"));

        TwitterPost expected = getExpectedTwitterPost("I am a fixture status");

        assertEquals(expected, twitterService.replyToTweet("this is a good reply message", 222L));
    }

    @Test
    public void testReplyToTweetMessageTooLong() {
        String replyMessage = RandomString.make(281);
        try {
            twitterService.replyToTweet(replyMessage,222L);
            fail("Expected Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Tweet reply cannot be null, empty white spaces, or longer than 280 characters.", e.getMessage());
        }
    }

    @Test
    public void testReplyToTweetMessageBlank() {
        String replyMessage = "   ";
        try {
            twitterService.replyToTweet(replyMessage,222L);
            fail("Expected Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Tweet reply cannot be null, empty white spaces, or longer than 280 characters.", e.getMessage());
        }
    }

    @Test
    public void testReplyToTweetMessageNull() {
        String replyMessage = null;
        try {
            twitterService.replyToTweet(replyMessage,222L);
            fail("Expected Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Tweet reply cannot be null, empty white spaces, or longer than 280 characters.", e.getMessage());
        }
    }

    @Test
    public void testReplyToTweetReplyToStatusIdNull() {
        Long replyToStatusId = null;
        try {
            twitterService.replyToTweet("a good reply message", replyToStatusId);
            fail("Expected Twitter Exception to be thrown");
        } catch (TwitterException e) {
            assertEquals("Status Id of tweet being replied to cannot be null.", e.getMessage());
        }
    }

    @Test
    public void testReplyToTweetException() throws TwitterException {
        String expectedErrorMessage = "There was an error interacting with the Twitter API and/or Twitter Keys.";

        when(twitterMock.updateStatus(any(StatusUpdate.class))).thenThrow(new TwitterException("mocking that something went wrong with Twitter"));

        try {
            twitterService.replyToTweet("Exception should be thrown here", 222L);
        } catch (TwitterException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }


    private Status getFixtureStatus(String text) {
        FixtureStatus fixtureStatus = new FixtureStatus();
        fixtureStatus.setCreatedAt(new Date(1514908981));
        fixtureStatus.setText(text);
        fixtureStatus.setId("12345");
        fixtureStatus.setInReplyToStatusId(222L);
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
        return new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), text, new Date(1514908981), "12345", 222L);
    }
}

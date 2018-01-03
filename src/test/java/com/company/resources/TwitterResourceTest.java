package com.company.resources;

import com.company.TwitterAppConfiguration;
import com.company.TwitterAppConfigurationKeys;
import com.company.api.TwitterErrorResponse;
import com.company.models.TwitterPost;
import com.company.models.TwitterUser;
import com.company.services.TwitterService;
import net.bytebuddy.utility.RandomString;
import org.junit.Before;
import org.junit.Test;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        when(configurationMock.getTwitterKeys()).thenReturn(mockKeys);
    }

    @Test
    public void addTweetGood() throws TwitterException {
        String val = "a good message";
        TwitterPost twitterPost = getTwitterPost("good post message");
        when(twitterServiceMock.postTweet(val, mockKeys)).thenReturn(twitterPost);
        Response expected = Response.ok(twitterPost).build();
        Response actual = twitterResource.addTweet(val);
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void addTweetBlankMessage() throws TwitterException {
        String val = "";
        String errorMessage = "Tweet cannot be null, empty white spaces, or longer than 280 characters.";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenThrow(new TwitterException(errorMessage));
        Response actual = twitterResource.addTweet(val);
        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }

    @Test
    public void addTweetNullMessage() throws TwitterException {
        String val = null;
        String errorMessage = "Tweet cannot be null, empty white spaces, or longer than 280 characters.";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenThrow(new TwitterException(errorMessage));
        Response actual = twitterResource.addTweet(val);
        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }

    @Test
    public void addTweetMessageTooLong() throws TwitterException {
        String val = RandomString.make(281);
        String errorMessage = "Tweet cannot be null, empty white spaces, or longer than 280 characters.";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenThrow(new TwitterException(errorMessage));
        Response actual = twitterResource.addTweet(val);
        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }

    @Test
    public void getTimelineGood() throws TwitterException {
        TwitterPost twitterPost = getTwitterPost("someone's good post");
        List<TwitterPost> expectedHomeTimeline = new ArrayList<>();
        expectedHomeTimeline.add(twitterPost);
        when(twitterServiceMock.getTimeline(mockKeys)).thenReturn(expectedHomeTimeline);
        Response actual = twitterResource.getTimeline();
        Response expected = Response.ok(expectedHomeTimeline).build();
        assertEquals(actual.getStatus(), expected.getStatus());
        assertEquals(actual.getStatusInfo(), expected.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }

    @Test
    public void getFilteredTimelineGood() throws TwitterException {
        List<TwitterPost> expectedFilteredTimeline = new ArrayList<>();
        expectedFilteredTimeline.add(getTwitterPost("someone's good post"));
        expectedFilteredTimeline.add(getTwitterPost("someone else's good post"));
        when(twitterServiceMock.getFilteredTimeline("good", mockKeys)).thenReturn(expectedFilteredTimeline);
        Response actual = twitterResource.getFilteredTimeline("good");
        Response expected = Response.ok(expectedFilteredTimeline).build();
        assertEquals(actual.getStatus(), expected.getStatus());
        assertEquals(actual.getStatusInfo(), expected.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }

    @Test
    public void getFilteredTimelineNullFilter() throws TwitterException {
        String filter = null;
        String errorMessage = "Filter parameter cannot be null or empty white spaces.";
        when(twitterServiceMock.getFilteredTimeline(filter, mockKeys)).thenThrow(new TwitterException(errorMessage));
        Response actual = twitterResource.getFilteredTimeline(filter);
        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        assertEquals(actual.getStatus(), expected.getStatus());
        assertEquals(actual.getStatusInfo(), expected.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }

    @Test
    public void getFilteredTimelineBlankFilter() throws TwitterException {
        String filter = "";
        String errorMessage = "Filter parameter cannot be null or empty white spaces.";
        when(twitterServiceMock.getFilteredTimeline(filter, mockKeys)).thenThrow(new TwitterException(errorMessage));
        Response actual = twitterResource.getFilteredTimeline(filter);
        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        assertEquals(actual.getStatus(), expected.getStatus());
        assertEquals(actual.getStatusInfo(), expected.getStatusInfo());
        assertEquals(actual.getEntity(), expected.getEntity());
    }


    private TwitterPost getTwitterPost(String text) {
        return new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), text, new Date(1514908981));
    }

}

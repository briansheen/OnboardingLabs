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
    public void testAddTweet() throws TwitterException {
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
    public void testAddTweetBlank() throws TwitterException {
        String val = "";
        String errorMessage = "Tweet cannot be null, empty white spaces, or longer than 280 characters.";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenThrow(new TwitterException(errorMessage));

        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        Response actual = twitterResource.addTweet(val);

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void testAddTweetNull() throws TwitterException {
        String val = null;
        String errorMessage = "Tweet cannot be null, empty white spaces, or longer than 280 characters.";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenThrow(new TwitterException(errorMessage));

        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        Response actual = twitterResource.addTweet(val);

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void testAddTweetTooLong() throws TwitterException {
        String val = RandomString.make(281);
        String errorMessage = "Tweet cannot be null, empty white spaces, or longer than 280 characters.";
        when(twitterServiceMock.postTweet(val, mockKeys)).thenThrow(new TwitterException(errorMessage));

        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        Response actual = twitterResource.addTweet(val);

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void testGetTimeline() throws TwitterException {
        TwitterPost twitterPost = getTwitterPost("someone's good post");
        List<TwitterPost> expectedHomeTimeline = new ArrayList<>();
        expectedHomeTimeline.add(twitterPost);
        when(twitterServiceMock.getTimeline(mockKeys)).thenReturn(expectedHomeTimeline);

        Response expected = Response.ok(expectedHomeTimeline).build();
        Response actual = twitterResource.getTimeline();

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void testGetFilteredTimeline() throws TwitterException {
        List<TwitterPost> expectedFilteredTimeline = new ArrayList<>();
        expectedFilteredTimeline.add(getTwitterPost("someone's good post"));
        expectedFilteredTimeline.add(getTwitterPost("someone else's good post"));
        when(twitterServiceMock.getFilteredTimeline("good", mockKeys)).thenReturn(expectedFilteredTimeline);

        Response expected = Response.ok(expectedFilteredTimeline).build();
        Response actual = twitterResource.getFilteredTimeline("good");

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void testGetFilteredTimelineNullFilter() throws TwitterException {
        String filter = null;
        String errorMessage = "Filter parameter cannot be null or empty white spaces.";
        when(twitterServiceMock.getFilteredTimeline(filter, mockKeys)).thenThrow(new TwitterException(errorMessage));

        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        Response actual = twitterResource.getFilteredTimeline(filter);

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }

    @Test
    public void testGetFilteredTimelineBlankFilter() throws TwitterException {
        String filter = "";
        String errorMessage = "Filter parameter cannot be null or empty white spaces.";
        when(twitterServiceMock.getFilteredTimeline(filter, mockKeys)).thenThrow(new TwitterException(errorMessage));

        Response expected = Response.status(Response.Status.NOT_FOUND).entity(new TwitterErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), errorMessage)).build();
        Response actual = twitterResource.getFilteredTimeline(filter);

        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getStatusInfo(), actual.getStatusInfo());
        assertEquals(expected.getEntity(), actual.getEntity());
    }


    private TwitterPost getTwitterPost(String text) {
        return new TwitterPost(new TwitterUser("Lab_9", "Lab Nine", "https://confluence.dev.lithium.com/x/8C5EBQ"), text, new Date(1514908981));
    }

}

package com.company.api;

import ch.qos.logback.core.status.Status;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class TwitterErrorResponseTest {
    private TwitterErrorResponse twitterErrorResponse;


    @Before
    public void setup() {
        twitterErrorResponse = new TwitterErrorResponse();
    }

    @Test
    public void testTwitterErrorResponseConstructorsAndGetters() {
        assertEquals(0, twitterErrorResponse.getErrorCode());
        assertNull(twitterErrorResponse.getErrorMessage());

        int errorCode = Response.Status.NOT_FOUND.getStatusCode();
        String errorMessage = Response.Status.NOT_FOUND.getReasonPhrase();

        twitterErrorResponse = new TwitterErrorResponse(errorCode, errorMessage);

        assertEquals(errorCode, twitterErrorResponse.getErrorCode());
        assertEquals(errorMessage, twitterErrorResponse.getErrorMessage());
    }

    @Test
    public void testTwitterErrorResponseHashAndEquals() {
        int errorCode = Response.Status.NOT_FOUND.getStatusCode();
        String errorMessage = Response.Status.NOT_FOUND.getReasonPhrase();

        twitterErrorResponse = new TwitterErrorResponse(errorCode, errorMessage);

        TwitterErrorResponse twitterErrorResponseCopy = new TwitterErrorResponse(errorCode, errorMessage);

        assertTrue(twitterErrorResponse.equals(twitterErrorResponseCopy));

        assertEquals(twitterErrorResponse.hashCode(), twitterErrorResponseCopy.hashCode());
    }

    @Test
    public void testTwitterErrorResponseSetters() {
        int errorCode = Response.Status.BAD_REQUEST.getStatusCode();
        String errorMessage = Response.Status.BAD_REQUEST.getReasonPhrase();

        twitterErrorResponse.setErrorCode(errorCode);
        twitterErrorResponse.setErrorMessage(errorMessage);

        assertEquals(errorCode, twitterErrorResponse.getErrorCode());
        assertEquals(errorMessage, twitterErrorResponse.getErrorMessage());
    }


}

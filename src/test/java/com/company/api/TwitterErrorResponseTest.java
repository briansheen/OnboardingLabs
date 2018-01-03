package com.company.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TwitterErrorResponseTest {
    private TwitterErrorResponse twitterErrorResponse;

    @Before
    public void setup(){
        twitterErrorResponse = new TwitterErrorResponse();
    }

    @Test
    public void testTwitterErrorResponseConstructorsAndGetters() {
        assertEquals(0, twitterErrorResponse.getErrorCode());
        assertNull(twitterErrorResponse.getErrorMessage());

        int errorCode = 404;
        String errorMessage = "Not Found";

        twitterErrorResponse = new TwitterErrorResponse(errorCode, errorMessage);

        assertEquals(errorCode, twitterErrorResponse.getErrorCode());
        assertEquals(errorMessage, twitterErrorResponse.getErrorMessage());
    }

    @Test
    public void testTwitterErrorResponseSetters(){
        int errorCode = 400;
        String errorMessage = "Bad Request";

        twitterErrorResponse.setErrorCode(errorCode);
        twitterErrorResponse.setErrorMessage(errorMessage);

        assertEquals(errorCode, twitterErrorResponse.getErrorCode());
        assertEquals(errorMessage, twitterErrorResponse.getErrorMessage());
    }


}

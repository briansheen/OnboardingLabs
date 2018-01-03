package com.company.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TwitterPostTest {
    private TwitterPost twitterPost;

    @Before
    public void setup(){
        twitterPost = new TwitterPost();
    }

    @Test
    public void testTwitterPostConstructorsAndGetters() {
        assertNull(twitterPost.getTwitterUser());
        assertNull(twitterPost.getMessage());
        assertNull(twitterPost.getCreatedAt());

        TwitterUser twitterUser = new TwitterUser("ClifBar", "CLIF Bar", "https://pbs.twimg.com/profile_images/880842933590568960/Lv_OB29W_400x400.jpg");
        String message = "Crunchy Peanut Butter";
        Date createdAt = new Date(1515019973);

        twitterPost = new TwitterPost(twitterUser, message, createdAt);

        assertEquals(twitterUser, twitterPost.getTwitterUser());
        assertEquals(message, twitterPost.getMessage());
        assertEquals(createdAt, twitterPost.getCreatedAt());
    }

    @Test
    public void testTwitterPostSetters(){
        TwitterUser twitterUser = new TwitterUser("ClifBar", "CLIF Bar", "https://pbs.twimg.com/profile_images/880842933590568960/Lv_OB29W_400x400.jpg");
        String message = "Crunchy Peanut Butter";
        Date createdAt = new Date(1515019973);

        twitterPost.setTwitterUser(twitterUser);
        twitterPost.setMessage(message);
        twitterPost.setCreatedAt(createdAt);

        assertEquals(twitterUser, twitterPost.getTwitterUser());
        assertEquals(message, twitterPost.getMessage());
        assertEquals(createdAt, twitterPost.getCreatedAt());
    }
}

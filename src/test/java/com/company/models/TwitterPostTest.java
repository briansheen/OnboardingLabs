package com.company.models;

import com.company.api.TwitterErrorResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TwitterPostTest {
    private TwitterPost twitterPost;

    @Before
    public void setup() {
        twitterPost = new TwitterPost();
    }

    @Test
    public void testTwitterPostConstructorsAndGetters() {
        assertNull(twitterPost.getTwitterUser());
        assertNull(twitterPost.getMessage());
        assertNull(twitterPost.getCreatedAt());
        assertNull(twitterPost.getId());
        assertNull(twitterPost.getInReplyToStatusId());

        TwitterUser twitterUser = new TwitterUser("ClifBar", "CLIF Bar", "https://pbs.twimg.com/profile_images/880842933590568960/Lv_OB29W_400x400.jpg");
        String message = "Crunchy Peanut Butter";
        Date createdAt = new Date(1515019973);
        String id = "12345";
        Long inReplyToStatusId = 222L;

        twitterPost = new TwitterPost(twitterUser, message, createdAt, id, inReplyToStatusId);

        assertEquals(twitterUser, twitterPost.getTwitterUser());
        assertEquals(message, twitterPost.getMessage());
        assertEquals(createdAt, twitterPost.getCreatedAt());
        assertEquals(id, twitterPost.getId());
        assertEquals(inReplyToStatusId, twitterPost.getInReplyToStatusId());
    }

    @Test
    public void testTwitterPostHashEqualsAndToString() {
        TwitterUser twitterUser = new TwitterUser("ClifBar", "CLIF Bar", "https://pbs.twimg.com/profile_images/880842933590568960/Lv_OB29W_400x400.jpg");
        String message = "Crunchy Peanut Butter";
        Date createdAt = new Date(1515019973);
        String id = "12345";
        Long inReplyToStatusId = 222L;

        twitterPost = new TwitterPost(twitterUser, message, createdAt, id, inReplyToStatusId);

        assertTrue(twitterPost.equals(twitterPost));

        TwitterPost twitterPostCopy = new TwitterPost(twitterUser, message, createdAt, id, inReplyToStatusId);

        assertEquals(twitterPost.hashCode(), twitterPostCopy.hashCode());
        assertEquals(twitterPost.toString(), twitterPostCopy.toString());

        twitterPostCopy.setTwitterUser(new TwitterUser());

        assertFalse(twitterPost.equals(twitterPostCopy));

        twitterPostCopy.setTwitterUser(twitterUser);
        twitterPostCopy.setMessage("Chocolate Chip");

        assertFalse(twitterPost.equals(twitterPostCopy));

        twitterPostCopy.setMessage(message);
        twitterPostCopy.setCreatedAt(new Date(1515099274));

        assertFalse(twitterPost.equals(twitterPostCopy));

        twitterPostCopy.setCreatedAt(createdAt);
        twitterPostCopy.setId("123456");

        assertFalse(twitterPost.equals(twitterPostCopy));

        twitterPostCopy.setId(id);
        twitterPostCopy.setInReplyToStatusId(223L);

        assertFalse(twitterPost.equals(twitterPostCopy));

        assertFalse(twitterPost.equals(null));
        assertFalse(twitterPost.equals(twitterUser));
    }

    @Test
    public void testTwitterPostSetters() {
        TwitterUser twitterUser = new TwitterUser("ClifBar", "CLIF Bar", "https://pbs.twimg.com/profile_images/880842933590568960/Lv_OB29W_400x400.jpg");
        String message = "Crunchy Peanut Butter";
        Date createdAt = new Date(1515019973);
        String id = "12345";
        Long inReplyToStatusId = 222L;

        twitterPost.setTwitterUser(twitterUser);
        twitterPost.setMessage(message);
        twitterPost.setCreatedAt(createdAt);
        twitterPost.setId(id);
        twitterPost.setInReplyToStatusId(inReplyToStatusId);

        assertEquals(twitterUser, twitterPost.getTwitterUser());
        assertEquals(message, twitterPost.getMessage());
        assertEquals(createdAt, twitterPost.getCreatedAt());
        assertEquals(id, twitterPost.getId());
        assertEquals(inReplyToStatusId, twitterPost.getInReplyToStatusId());
    }
}

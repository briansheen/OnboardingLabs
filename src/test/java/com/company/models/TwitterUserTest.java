package com.company.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterUserTest {
    private TwitterUser twitterUser;

    @Before
    public void setup() {
        twitterUser = new TwitterUser();
    }

    @Test
    public void testTwitterUserConstructorsAndGetters() {
        assertNull(twitterUser.getTwitterHandle());
        assertNull(twitterUser.getName());
        assertNull(twitterUser.getProfileImageUrl());

        String twitterHandle = "TopoChicoUSA";
        String name = "Topo Chico";
        String profileImageUrl = "https://pbs.twimg.com/profile_images/764646642087710721/iXQPYQt2_400x400.jpg";

        twitterUser = new TwitterUser(twitterHandle, name, profileImageUrl);

        assertEquals(twitterHandle, twitterUser.getTwitterHandle());
        assertEquals(name, twitterUser.getName());
        assertEquals(profileImageUrl, twitterUser.getProfileImageUrl());
    }

    @Test
    public void testTwitterUserHashEqualsAndToString() {
        String twitterHandle = "TopoChicoUSA";
        String name = "Topo Chico";
        String profileImageUrl = "https://pbs.twimg.com/profile_images/764646642087710721/iXQPYQt2_400x400.jpg";

        twitterUser = new TwitterUser(twitterHandle, name, profileImageUrl);

        TwitterUser twitterUserCopy = new TwitterUser(twitterHandle, name, profileImageUrl);

        assertTrue(twitterUser.equals(twitterUserCopy));

        assertEquals(twitterUser.hashCode(),twitterUserCopy.hashCode());

        assertEquals(twitterUser.toString(), twitterUserCopy.toString());
    }

    @Test
    public void testTwitterUserSetters() {
        String twitterHandle = "TopoChicoUSA";
        String name = "Topo Chico";
        String profileImageUrl = "https://pbs.twimg.com/profile_images/764646642087710721/iXQPYQt2_400x400.jpg";

        twitterUser.setTwitterHandle(twitterHandle);
        twitterUser.setName(name);
        twitterUser.setProfileImageUrl(profileImageUrl);

        assertEquals(twitterHandle, twitterUser.getTwitterHandle());
        assertEquals(name, twitterUser.getName());
        assertEquals(profileImageUrl, twitterUser.getProfileImageUrl());
    }
}

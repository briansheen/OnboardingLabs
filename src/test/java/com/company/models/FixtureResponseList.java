package com.company.models;

import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;

import java.util.*;

public class FixtureResponseList<T> extends ArrayList<T> implements ResponseList<T> {

    @Override
    public RateLimitStatus getRateLimitStatus() {
        return null;
    }

    @Override
    public int getAccessLevel() {
        return 0;
    }
}

package com.company;

import com.company.services.TwitterService;
import dagger.Component;
import twitter4j.Twitter;

@Component(modules = {TwitterModule.class})
public interface TwitterComponent {
    TwitterService getTwitterService();
}

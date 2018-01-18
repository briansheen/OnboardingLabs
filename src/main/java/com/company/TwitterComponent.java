package com.company;

import com.company.resources.TwitterResource;
import dagger.Component;

@Component(modules = {TwitterModule.class})
public interface TwitterComponent {
    TwitterResource getTwitterResource();
}

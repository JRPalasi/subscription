package com.adidas.subscription.service;

import java.util.List;

import com.adidas.subscription.entity.Subscription;

public interface ISubscriptionService {

    Subscription create(Subscription subscription);
    boolean cancel( long id );
    List<Subscription> list();
    Subscription get( long id);


}

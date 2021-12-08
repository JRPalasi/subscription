package com.adidas.subscription.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.service.repository.SubscriptionRepository;
import com.adidas.subscription.service.ISubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    SubscriptionRepository repository;

    @Override
    public Subscription create(Subscription subscription) {
        return repository.save(subscription);
    }

    @Override
    public boolean cancel(long id) throws NoSuchElementException {
        boolean cancelled = false;
        if (repository.existsById(id)) {
            repository.deleteById(id);
            cancelled = true;
        } else {
            throw new NoSuchElementException(String.format("No subscription found with id %s", id));
        }

        return cancelled;
    }

    @Override
    public List<Subscription> list() {
        return repository.findAll();
    }

    @Override
    public Subscription get(long id) throws NoSuchElementException{
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No subscription with id=%s", id)));
    }


}

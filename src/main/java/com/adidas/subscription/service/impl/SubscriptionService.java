package com.adidas.subscription.service.impl;

import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.service.ISubscriptionService;
import com.adidas.subscription.service.repository.SubscriptionRepository;
import com.adidas.subscription.util.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementation of the subscriptions service operations using a JPA repository
 */
@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    SubscriptionRepository repository;

    /**
     * @param subscription Subscription DTO with the data for the system
     * @return
     */
    @Override
    public SubscriptionDTO create(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = DTOUtils.toSubscription(subscriptionDTO);
        Subscription created = repository.save(subscription);
        return DTOUtils.toSubscriptionDTO(created);
    }

    /**
     * Deletes the subscription identified by Id from the system
     *
     * @param id Identifier of the subscription.
     * @return true if deleted
     * @throws NoSuchElementException if the subscription is not found
     */
    @Override
    public boolean cancel(long id) throws NoSuchElementException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException(String.format("No subscription found with id %s", id));
        }

        return true;
    }

    /**
     * @return
     */
    @Override
    public List<SubscriptionDTO> list() {
        List<SubscriptionDTO> result;
        List<Subscription> subscriptions = repository.findAll();
        if (subscriptions.isEmpty()) {
            result = Collections.emptyList();
        } else {
            result = subscriptions.stream().map(DTOUtils::toSubscriptionDTO).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * Retrieves the subscription identified by the parameter Id.
     *
     * @param id Identifier of the subscription
     * @return Subscription identified by id
     * @throws NoSuchElementException if the subscription is not found
     */
    @Override
    public SubscriptionDTO get(long id) throws NoSuchElementException {
        return repository.findById(id)
                .map(DTOUtils::toSubscriptionDTO)
                .orElseThrow(() -> new NoSuchElementException(String.format("No subscription with id=%s", id)));
    }


}

package com.adidas.subscription.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;

/**
 * Interface for subscriptions service operations
 */
public interface ISubscriptionService {

    /**
     * Creates a new subscription in the system
     * @param subscription Subscription DTO with the data for the system
     * @return Created Subscription in the system
     */
    SubscriptionDTO create(SubscriptionDTO subscription);

    /**
     * Deletes the subscription identified by Id from the system
     * @param id Identifier of the subscription.
     * @return true if deleted
     * @throws NoSuchElementException if the subscription is not found
     */
    boolean cancel(long id) throws NoSuchElementException;

    /**
     * Lists all the subscriptions in the system
     * @return List of Subscriptions in the system
     */
    List<SubscriptionDTO> list();

    /**
     * Retrieves the subscription identified by the parameter Id.
     * @param id Identifier of the subscription
     * @return The subscription identified
     * @throws NoSuchElementException if the subscription is not found
     */
    SubscriptionDTO get( long id) throws NoSuchElementException;


}

package com.adidas.subscription.util;

import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;
import org.modelmapper.ModelMapper;

public class DTOUtils {

    private DTOUtils() {
        throw new IllegalStateException("Utility class for static use");
    }

    public static Subscription toSubscription(SubscriptionDTO subscriptionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subscriptionDTO, Subscription.class);
    }

    public static SubscriptionDTO toSubscriptionDTO(Subscription subscription) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }
}

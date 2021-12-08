package com.adidas.subscription.service.repository;

import com.adidas.subscription.entity.Subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}

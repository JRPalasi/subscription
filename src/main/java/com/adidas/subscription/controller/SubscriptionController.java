package com.adidas.subscription.controller;

import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.service.impl.SubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionService service;

    @PutMapping("/subscriptions")
    public ResponseEntity<Long> create(@RequestBody Subscription subscription) {
        Subscription created = service.create(subscription);
        return created.getId() != null ? ResponseEntity.ok(created.getId()) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        boolean cancelled;
        try {
             cancelled = service.cancel(id);
        }catch ( NoSuchElementException e ){
            cancelled = false;
        }

        return cancelled ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> list() {
        List<Subscription> subscriptions = service.list();
        return subscriptions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/subscriptions/{id}")
    public ResponseEntity<Subscription> get(@PathVariable Long id) {
        Subscription subscription;
        try {
             subscription = service.get(id);
        }catch ( NoSuchElementException e ){
            subscription = null;
        }
        return subscription != null ? ResponseEntity.ok(subscription) : ResponseEntity.notFound().build();
    }

}

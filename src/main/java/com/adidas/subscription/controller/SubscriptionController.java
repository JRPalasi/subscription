package com.adidas.subscription.controller;

import com.adidas.subscription.dto.SubscriptionDTO;
import com.adidas.subscription.entity.Subscription;
import com.adidas.subscription.service.impl.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Tag(name = "SubscriptionService", description = "Subscription internal rest service endpoint for managing the subscriptions in the Subscription systems")
public class SubscriptionController {

    @Autowired
    SubscriptionService service;

    @PutMapping("/subscriptions")
    @Operation(summary = "Creates a new subscription in the subscription in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription created, returns Id of the created subscription"),
            @ApiResponse(responseCode = "400", description = "Could not create subscription")})
    public ResponseEntity<Long> create(@RequestBody
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Subscription data information")
                                               SubscriptionDTO subscription) {
        SubscriptionDTO created = service.create(subscription);
        return created.getId() != null ? ResponseEntity.ok(created.getId()) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/subscriptions/{id}")
    @Operation(summary = "Cancels a subscription identified by Id in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription cancelled"),
            @ApiResponse(responseCode = "404", description = "Subscription does not exist")})
    public ResponseEntity<?> cancel(@Parameter(required = true, description = "Id of the subscription to be cancelled")
                                    @PathVariable Long id) {
        boolean cancelled;
        try {
            cancelled = service.cancel(id);
        } catch (NoSuchElementException e) {
            cancelled = false;
        }

        return cancelled ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/subscriptions")
    @Operation(summary = "Lists all subscriptions in the subscription system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of subscriptions"),
            @ApiResponse(responseCode = "204", description = "There are no subscriptions in the system")})
    public ResponseEntity<List<SubscriptionDTO>> list() {
        List<SubscriptionDTO> subscriptions = service.list();
        return subscriptions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/subscriptions/{id}")
    @Operation(summary = "Retrieves a subscription in the subscription system identified by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription retrieved properly"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")})
    public ResponseEntity<SubscriptionDTO> get(@Parameter(required = true, description = "Id of the subscription to be retrieved")
                                            @PathVariable Long id) {
        SubscriptionDTO subscription;
        try {
            subscription = service.get(id);
        } catch (NoSuchElementException e) {
            subscription = null;
        }
        return subscription != null ? ResponseEntity.ok(subscription) : ResponseEntity.notFound().build();
    }

}

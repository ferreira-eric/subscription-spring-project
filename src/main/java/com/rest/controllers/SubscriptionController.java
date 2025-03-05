package com.rest.controllers;

import com.dtos.SubscriptionDTO;
import com.exceptions.StatusNotFoundException;
import com.rest.api.SubscriptionAPI;
import com.service.EventHistoryService;
import com.service.StatusService;
import com.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController implements SubscriptionAPI {

    @Autowired
    private StatusService statusService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private EventHistoryService eventHistoryService;

    @Override
    public ResponseEntity<Object> createSubscription(SubscriptionDTO subscriptionDTO) {
        if(subscriptionService.userHasSubscription(subscriptionDTO.getUserId())) {
            return badRequest().build();
        }

        var subscriptionDto = subscriptionService.create(subscriptionDTO);

        eventHistoryService.createEventHistory(subscriptionDto, subscriptionDto.getStatusId());

        return ok(subscriptionDto);
    }

    @Override
    public ResponseEntity<Object> canceledSubscription(Long idSubscription){
        try {
            var subscription = subscriptionService.findById(idSubscription);
            statusService.updateStatusToCanceled(subscription);

            eventHistoryService.createEventHistory(subscription, subscription.getStatusId());

            return ok().build();
        }
        catch (StatusNotFoundException ex) {
            return badRequest().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> restartedSubscription(Long idSubscription){
        try {
            var subscription = subscriptionService.findById(idSubscription);
            statusService.updateStatusToRestarted(subscription);

            eventHistoryService.createEventHistory(subscription, subscription.getStatusId());

            return ok().build();
        }
        catch (Exception ex){
            return badRequest().body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getSubscriptionById(Long idSubscription) {
        var subscription = subscriptionService.findById(idSubscription);

        return ok(subscription);
    }

    @Override
    public ResponseEntity<List<?>> getAllSubscription() {
        var subscriptions = subscriptionService.findAll();

        return ok(subscriptions);
    }
}

package com.rest.controllers;

import com.dtos.SubscriptionDTO;
import com.exceptions.StatusNotFoundException;
import com.rest.api.SubscriptionAPI;
import com.service.EventHistoryService;
import com.service.StatusService;
import com.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var subscription = subscriptionService.create(subscriptionDTO);

        eventHistoryService.createEventHistory(subscription, subscription.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
    }

    @Override
    public ResponseEntity<Object> canceledSubscription(Long idSubscription){
        try {
            var subscription = subscriptionService.findById(idSubscription);
            statusService.statusCanceled(subscription.getStatusId());

            eventHistoryService.createEventHistory(subscription, subscription.getStatusId());

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (StatusNotFoundException st) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(st.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> restartedSubscription(Long idSubscription){
        try {
            var statusId = subscriptionService.restartedSubscription(idSubscription);
            var subscription = subscriptionService.findById(idSubscription);

            eventHistoryService.createEventHistory(subscription, statusId);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getSubscriptionById(Long idSubscription) {
        var subscription = subscriptionService.findById(idSubscription);

        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }

    @Override
    public ResponseEntity<List<?>> getAllSubscription() {
        var subscription = subscriptionService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }
}

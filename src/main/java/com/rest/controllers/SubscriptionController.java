package com.rest.controllers;

import com.dtos.SubscriptionDTO;
import com.exceptions.StatusNotFoundException;
import com.repository.EventHistoryRepository;
import com.repository.StatusRepository;
import com.repository.SubscriptionRepository;
import com.repository.entity.EventHistory;
import com.repository.entity.Status;
import com.repository.entity.Subscription;
import com.rest.api.SubscriptionAPI;
import com.service.StatusService;
import com.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController implements SubscriptionAPI {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EventHistoryRepository eventRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public ResponseEntity<Subscription> createSubscription(SubscriptionDTO subscriptionDTO) {

        Subscription subscription = Subscription.deserialize(subscriptionDTO);

        if(subscriptionService.userHasSubscription(subscription.getUserId())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Status status = statusService.createStatus();
        subscription.setStatusId(status.getId());

        Subscription subs = subscriptionRepository.save(subscription);

        createEventHistory(subs.getId(), status.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(subs);
    }

    @Override
    public ResponseEntity<Object> canceledSubscription(UUID idSubscription){
        try {
            Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

            if(subscription.isPresent()){
                Subscription subs = subscription.get();
                statusService.statusCanceled(subs.getStatusId());

                createEventHistory(idSubscription, subs.getStatusId());

                return ResponseEntity.status(HttpStatus.OK).build();
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (StatusNotFoundException st) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(st.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> restartedSubscription(UUID idSubscription){
        try {
            Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

            if (subscription.isPresent()) {
                Subscription subs = subscription.get();
                statusService.statusRestarted(subs.getStatusId());

                createEventHistory(idSubscription, subs.getStatusId());

                return ResponseEntity.status(HttpStatus.OK).build();
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (StatusNotFoundException st){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(st.getMessage());
        }
    }

    @Override
    public ResponseEntity<Optional<Subscription>> getSubscriptionById(UUID idSubscription) {

        Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

        if(subscription.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }

    @Override
    public ResponseEntity<List<Subscription>> getAllSubscription() {
        List<Subscription> subscription = subscriptionRepository.findAll();

        if(subscription.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }

    protected void createEventHistory(UUID idSubscription, UUID idStatus) {
        Optional<Status> statusOpt = statusRepository.findById(idStatus);

        if(statusOpt.isPresent()){
            Status status = statusOpt.get();
            EventHistory event = new EventHistory();

            event.setSubscriptionId(idSubscription);
            event.setType(status.getStatusName().getValue());
            eventRepository.save(event);
        }
    }
}

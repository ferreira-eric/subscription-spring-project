package com.springpoo2023.rest.controllers;

import com.springpoo2023.dtos.SubscriptionDTO;
import com.springpoo2023.repository.EventHistoryRepository;
import com.springpoo2023.repository.StatusRepository;
import com.springpoo2023.repository.SubscriptionRepository;
import com.springpoo2023.repository.entity.EventHistory;
import com.springpoo2023.repository.entity.Status;
import com.springpoo2023.repository.entity.Subscription;
import com.springpoo2023.rest.api.SubscriptionAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.springpoo2023.utils.enums.StatusEnum.*;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController implements SubscriptionAPI {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EventHistoryRepository eventRepository;

    @Override
    public ResponseEntity<Subscription> createSubscription(SubscriptionDTO subscriptionDTO) {

        Subscription subscription = new Subscription();
        BeanUtils.copyProperties(subscriptionDTO, subscription);

        Status status = createStatus();
        subscription.setStatusId(status.getId());

        Subscription subs = subscriptionRepository.save(subscription);

        createEventHistory(subs.getId(), status.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(subs);
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

    @Override
    public ResponseEntity<Object> canceledSubscription(UUID idSubscription) {
        Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

        if(subscription.isPresent()){
            Subscription subs = subscription.get();
            statusCanceled(subs.getStatusId());

            createEventHistory(idSubscription, subs.getStatusId());

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<Object> restartedSubscription(UUID idSubscription) {
        Optional<Subscription> subscription = subscriptionRepository.findById(idSubscription);

        if(subscription.isPresent()){
            Subscription subs = subscription.get();
            statusRestarted(subs.getStatusId());

            createEventHistory(idSubscription, subs.getStatusId());

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    private Status createStatus() {
        Status status = new Status();
        status.setStatusName(SUBSCRIPTION_PURCHASED);

        return statusRepository.save(status);
    }

    private void statusCanceled(UUID idStatus) {
        Status status = statusRepository.getReferenceById(idStatus);
        status.setStatusName(SUBSCRIPTION_CANCELED);

        statusRepository.save(status);
    }

    private void statusRestarted(UUID idStatus) {
        Status status = statusRepository.getReferenceById(idStatus);

        status.setStatusName(SUBSCRIPTION_RESTARTED);

        statusRepository.save(status);
    }

    private void createEventHistory(UUID idSubscription,UUID idStatus) {
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

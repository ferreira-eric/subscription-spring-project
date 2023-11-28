package com.springpoo2023.rest.controllers;

import com.springpoo2023.dtos.SubscriptionDTO;
import com.springpoo2023.repository.SubscriptionRepository;
import com.springpoo2023.repository.entity.Status;
import com.springpoo2023.repository.entity.Subscription;
import com.springpoo2023.rest.api.SubscriptionAPI;
import com.springpoo2023.service.StatusService;
import org.springframework.beans.BeanUtils;
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

    private StatusService statusService;

    @Override
    public ResponseEntity<Subscription> createSubscription(SubscriptionDTO subscriptionDTO) {

        Subscription subscription = new Subscription();

        Status status = statusService.createStatus();
        subscription.setStatusId(status.getId());

        BeanUtils.copyProperties(subscriptionDTO, subscription);

        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionRepository.save(subscription));
    }

    @Override
    public ResponseEntity<Optional<Subscription>> getSubscriptionById(UUID idSubscription) {
        return null; //TODO
    }

    @Override
    public ResponseEntity<List<Subscription>> getAllSubscription() {
        return null; //TODO
    }

    @Override
    public ResponseEntity<Object> canceledSubscription(UUID idSubscription) {

        Subscription subscription = subscriptionRepository.getReferenceById(idSubscription);

        statusService.statusCanceled(subscription.getStatusId());

        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }

    @Override
    public ResponseEntity<Object> restartedSubscription(UUID idSubscription) {
        Subscription subscription = subscriptionRepository.getReferenceById(idSubscription);

        statusService.statusRestarted(subscription.getStatusId());

        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }
}

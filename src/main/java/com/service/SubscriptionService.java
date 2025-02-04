package com.service;

import com.repository.SubscriptionRepository;
import com.repository.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public boolean userHasSubscription(UUID idUser) {

        Optional<Subscription> subscription = subscriptionRepository.findByUserId(idUser);

        return subscription.isPresent();
    }
}

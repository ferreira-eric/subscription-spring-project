package com.springpoo2023.service;

import com.springpoo2023.repository.SubscriptionRepository;
import com.springpoo2023.repository.entity.Subscription;
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

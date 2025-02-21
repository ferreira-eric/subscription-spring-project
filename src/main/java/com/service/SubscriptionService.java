package com.service;

import com.dtos.SubscriptionDTO;
import com.exceptions.EntityNotFoundException;
import com.repository.SubscriptionRepository;
import com.repository.entity.Status;
import com.repository.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private StatusService statusService;

    public boolean userHasSubscription(Long idUser) {
        return subscriptionRepository.findByUserId(idUser).isPresent();
    }

    public SubscriptionDTO create(SubscriptionDTO subscriptionDto) {
        Status status = statusService.createStatus();
        subscriptionDto.setStatusId(status.getId());

        return SubscriptionDTO.deserialize(subscriptionRepository.save(Subscription.deserialize(subscriptionDto)));
    }

    public SubscriptionDTO findById(Long id) {
        return subscriptionRepository.findById(id)
                .map(SubscriptionDTO::deserialize)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", id));
    }

    public Long restartedSubscription(Long id) throws Exception {
        var subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", id));

        statusService.updateStatusToRestarted(subscription.getStatus().getId());

        return subscription.getStatus().getId();
    }

    public List<SubscriptionDTO> findAll() {
        return subscriptionRepository.findAll()
                .stream()
                .map(SubscriptionDTO::deserialize)
                .collect(Collectors.toList());
    }

}

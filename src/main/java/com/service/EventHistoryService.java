package com.service;

import com.dtos.SubscriptionDTO;
import com.repository.EventHistoryRepository;
import com.repository.entity.EventHistory;
import com.repository.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventHistoryService {

    @Autowired
    private EventHistoryRepository eventHistoryRepository;

    @Autowired
    private StatusService statusService;

    public void createEventHistory(SubscriptionDTO subscriptionDTO, Long idStatus) {
        var status = statusService.getById(idStatus);

        var eventHistory = EventHistory.builder()
                .subscription(Subscription.deserialize(subscriptionDTO))
                .type(status.getStatusName())
                .build();

        eventHistoryRepository.save(eventHistory);
    }
}

package com.service;

import com.repository.EventHistoryRepository;
import com.repository.entity.EventHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventHistoryService {

    @Autowired
    private EventHistoryRepository eventHistoryRepository;

    @Autowired
    private StatusService statusService;

    public void createEventHistory(UUID idSubscription, UUID idStatus) {
        var status = statusService.findById(idStatus);

        var eventHistory = EventHistory.builder()
                .subscriptionId(idSubscription)
                .type(status.getStatusName().getValue())
                .build();

        eventHistoryRepository.save(eventHistory);
    }
}

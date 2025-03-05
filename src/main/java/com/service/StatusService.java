package com.service;

import com.dtos.SubscriptionDTO;
import com.exceptions.EntityNotFoundException;
import com.exceptions.StatusNotFoundException;
import com.repository.StatusRepository;
import com.repository.entity.Status;
import com.repository.entity.Subscription;
import com.utils.enums.StatusEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.utils.enums.StatusEnum.*;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    private final ModelMapper modelMapper = new ModelMapper();

    public Status getStatusByStatusEnum(StatusEnum statusName) {
        return statusRepository.getByStatusName(statusName);
    }

    public void updateStatusToCanceled(SubscriptionDTO subscriptionDTO) throws StatusNotFoundException{
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);

        if(statusIsCanceled(statusRepository
                .getReferenceById(subscription.getStatus().getId())))  throw new StatusNotFoundException();

        subscription.setStatus(statusRepository.getByStatusName(SUBSCRIPTION_CANCELED));

        subscriptionService.update(subscription.getId(), subscription);
    }

    public void updateStatusToRestarted(SubscriptionDTO subscriptionDTO) throws StatusNotFoundException{
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);

        if(!statusIsCanceled(statusRepository
                .getReferenceById(subscription.getStatus().getId())))  throw new StatusNotFoundException();

        subscription.setStatus(statusRepository.getByStatusName(SUBSCRIPTION_RESTARTED));

        subscriptionService.update(subscription.getId(), subscription);
    }

    public Status getById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status", id));
    }

    private static boolean statusIsCanceled(Status status) {
        return status.getStatusName().equals(SUBSCRIPTION_CANCELED);
    }
}

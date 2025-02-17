package com.service;

import com.exceptions.EntityNotFoundException;
import com.exceptions.StatusNotFoundException;
import com.repository.StatusRepository;
import com.repository.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.utils.enums.StatusEnum.*;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status createStatus() {
        var status = Status.builder()
                .statusName(SUBSCRIPTION_PURCHASED)
                .build();

        return statusRepository.save(status);
    }

    public void statusCanceled(Long id) throws StatusNotFoundException{
        Status status = statusRepository.getReferenceById(id);

        if(statusIsCanceled(status))  throw new StatusNotFoundException();

        status.setStatusName(SUBSCRIPTION_CANCELED);
        statusRepository.save(status);
    }

    public void statusRestarted(Long id) throws StatusNotFoundException{
        Status status = statusRepository.getReferenceById(id);

        if(!statusIsCanceled(status))  throw new StatusNotFoundException();

        status.setStatusName(SUBSCRIPTION_RESTARTED);
        statusRepository.save(status);
    }

    public Status findById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status", id));
    }

    private static boolean statusIsCanceled(Status status) {
        return status.getStatusName().equals(SUBSCRIPTION_CANCELED);
    }
}

package com.springpoo2023.service;

import com.springpoo2023.exceptions.StatusNotFoundException;
import com.springpoo2023.repository.StatusRepository;
import com.springpoo2023.repository.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.springpoo2023.utils.enums.StatusEnum.*;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status createStatus() {
        Status status = new Status();
        status.setStatusName(SUBSCRIPTION_PURCHASED);

        return statusRepository.save(status);
    }

    public void statusCanceled(UUID idStatus) throws StatusNotFoundException{
        Status status = statusRepository.getReferenceById(idStatus);

        if(statusIsCanceled(status))  throw new StatusNotFoundException();

        status.setStatusName(SUBSCRIPTION_CANCELED);
        statusRepository.save(status);
    }

    public void statusRestarted(UUID idStatus) throws StatusNotFoundException{
        Status status = statusRepository.getReferenceById(idStatus);

        if(!statusIsCanceled(status))  throw new StatusNotFoundException();

        status.setStatusName(SUBSCRIPTION_RESTARTED);
        statusRepository.save(status);
    }

    private static boolean statusIsCanceled(Status status) {
        return status.getStatusName().getValue().equalsIgnoreCase(SUBSCRIPTION_CANCELED.getValue());
    }
}

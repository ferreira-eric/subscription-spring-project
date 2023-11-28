package com.springpoo2023.service;

import com.springpoo2023.repository.StatusRepository;
import com.springpoo2023.repository.entity.Status;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.springpoo2023.utils.enums.StatusEnum.*;

@Service
public class StatusService {

    private StatusRepository statusRepository;

    public Status createStatus() {
        Status status = new Status();
        status.setStatusName(SUBSCRIPTION_PURCHASED);

        statusRepository.save(status);

        return status;
    }

    public void statusCanceled(UUID idStatus) {
        Status status = statusRepository.getReferenceById(idStatus);

        status.setStatusName(SUBSCRIPTION_CANCELED);

        statusRepository.save(status);
    }

    public void statusRestarted(UUID idStatus) {
        Status status = statusRepository.getReferenceById(idStatus);

        status.setStatusName(SUBSCRIPTION_RESTARTED);

        statusRepository.save(status);
    }
}

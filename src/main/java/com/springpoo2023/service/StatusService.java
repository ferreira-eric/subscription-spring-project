package com.springpoo2023.service;

import com.springpoo2023.repository.StatusRepository;
import com.springpoo2023.repository.entity.Status;

import static com.springpoo2023.utils.enums.StatusEnum.*;

public class StatusService {

    private StatusRepository statusRepository;

    public Status createStatus()
    {
        Status status = new Status();
        status.setStatusName(SUBSCRIPTION_PURCHASED);

        statusRepository.save(status);

        return status;
    }
}

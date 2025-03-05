package com.service;

import com.dtos.SubscriptionDTO;
import com.exceptions.EntityNotFoundException;
import com.repository.SubscriptionRepository;
import com.repository.entity.Subscription;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.utils.enums.StatusEnum.SUBSCRIPTION_WAITING_FOR_PAYMENT;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private StatusService statusService;

    private final ModelMapper modelMapper = new ModelMapper();

    public boolean userHasSubscription(Long idUser) {
        return subscriptionRepository.findByUserId(idUser).isPresent();
    }

    public SubscriptionDTO create(SubscriptionDTO subscriptionDto) {
        subscriptionDto.setStatusId(statusService.getStatusByStatusEnum(SUBSCRIPTION_WAITING_FOR_PAYMENT).getId());

        return modelMapper.map(subscriptionRepository.save(modelMapper.map(subscriptionDto, Subscription.class)),
                SubscriptionDTO.class);
    }

    public SubscriptionDTO findById(Long id) {
        return subscriptionRepository.findById(id)
                .map(subscription -> modelMapper.map(subscription, SubscriptionDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Subscription", id));
    }

    public List<SubscriptionDTO> findAll() {
        return subscriptionRepository.findAll()
                .stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionDTO.class))
                .collect(Collectors.toList());
    }

    public void update(Long id, Subscription subscription) {
        Subscription subscriptionEntity = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", id));

        subscription.setUpdatedAt(LocalDateTime.now());
        subscription.setCreatedAt(null);

        BeanUtils.copyProperties(subscription, subscriptionEntity);
        subscriptionRepository.save(subscriptionEntity);
    }

}

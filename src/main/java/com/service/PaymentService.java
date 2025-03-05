package com.service;

import com.dtos.PaymentDTO;
import com.exceptions.EntityNotFoundException;
import com.repository.PaymentRepository;
import com.repository.SubscriptionRepository;
import com.repository.entity.Payment;
import com.utils.enums.PaymentStatus;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.utils.enums.StatusEnum.SUBSCRIPTION_RESTARTED;
import static com.utils.enums.StatusEnum.SUBSCRIPTION_WAITING_FOR_PAYMENT;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public boolean paymentCanBeProcessed(PaymentDTO paymentDTO) throws Exception {
        var payment = paymentRepository.findById(paymentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Payment", paymentDTO.getId()));

        if (paymentDTO.getAmount().compareTo(payment.getAmount()) != 0) {
            throw new Exception("Payment amount does not match");
        }

        var subscription = subscriptionRepository.findById(paymentDTO.getSubscriptionId());

        var subs = subscription.orElseThrow(() ->
                new EntityNotFoundException("Subscription", paymentDTO.getSubscriptionId()));

        return subs.getStatus().getStatusName().equals(SUBSCRIPTION_WAITING_FOR_PAYMENT)
                || subs.getStatus().getStatusName().equals(SUBSCRIPTION_RESTARTED);
    }

    @Transactional
    public PaymentDTO createNewPayment(PaymentDTO paymentDTO) {
        paymentDTO.setStatus(PaymentStatus.PENDING);

        return modelMapper.map(paymentRepository.save(modelMapper.map(paymentDTO, Payment.class)), PaymentDTO.class);
    }

    public PaymentDTO findById(Long id) {
        return paymentRepository.findById(id)
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .orElseThrow(() -> new EntityNotFoundException("Payment", id));
    }

    public List<PaymentDTO> findAll() {
        return subscriptionRepository.findAll()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    public void update(Long id, Payment payment) {
        Payment paymentEntity = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment", id));

        paymentEntity.setUpdatedAt(LocalDateTime.now());
        paymentEntity.setCreatedAt(null);

        BeanUtils.copyProperties(payment, paymentEntity);
        paymentRepository.save(paymentEntity);
    }

    public boolean subscriptionHasPayment(Long idSubscription) {
        return paymentRepository.findBySubscriptionId(idSubscription).isPresent();
    }
}

package com.rest.controllers;

import com.dtos.PaymentDTO;
import com.rest.api.PaymentAPI;
import com.service.PaymentService;
import com.service.StatusService;
import com.service.SubscriptionService;
import com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController implements PaymentAPI {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private StatusService statusService;

    @Override
    public ResponseEntity<Object> createPayment(PaymentDTO paymentDTO) {
        if(paymentService.subscriptionHasPayment(paymentDTO.getSubscriptionId())){
            return badRequest().build();
        }

        var payment = paymentService.createNewPayment(paymentDTO);

        return ok(payment);
    }

    @Override
    public ResponseEntity<?> makePayment(PaymentDTO paymentDTO) {
        try {
            if (!paymentService.paymentCanBeProcessed(paymentDTO)) {
                return badRequest().build();
            }

            var subscription = subscriptionService.findById(paymentDTO.getSubscriptionId());

            statusService.updateStatusToPurchased(subscription);

            transactionService.createTransaction(paymentDTO);

            return ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getPaymentById(Long id) {
        var payment = paymentService.findById(id);

        return ok(payment);
    }

    @Override
    public ResponseEntity<List<?>> getAllPayments() {
        var payments = paymentService.findAll();

        return ok(payments);
    }
}

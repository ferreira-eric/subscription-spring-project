package com.rest.controllers;

import com.dtos.SubscriptionDTO;
import com.rest.api.PaymentAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController implements PaymentAPI {

    @Override
    public ResponseEntity<Object> createPayment(SubscriptionDTO subscriptionDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getPaymentById(Long idSubscription) {
        return null;
    }

    @Override
    public ResponseEntity<List<?>> getAllPayments() {
        return null;
    }

    //TODO quando pagamento acontecer colocar subscription para purchaded
}

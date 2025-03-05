package com.rest.api;

import com.dtos.PaymentDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentAPI {

    @PostMapping("/create")
    ResponseEntity<Object> createPayment(@RequestBody @Valid PaymentDTO paymentDTO);

    @Transactional
    @PostMapping("/pay")
    ResponseEntity<?> makePayment(@RequestBody @Valid PaymentDTO paymentDTO);

    @GetMapping("/{id}")
    ResponseEntity<Object> getPaymentById(@PathVariable(value = "id") Long id);

    @GetMapping("/all")
    ResponseEntity<List<?>> getAllPayments();
}

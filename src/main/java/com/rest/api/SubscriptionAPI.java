package com.rest.api;

import com.dtos.SubscriptionDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SubscriptionAPI {

    @PostMapping("/create")
    ResponseEntity<Object> createSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO);

    @GetMapping("/{id}")
    ResponseEntity<Object> getSubscriptionById(@PathVariable(value = "id") Long idSubscription);

    @GetMapping("/all")
    ResponseEntity<List<?>> getAllSubscription();

    @PostMapping("/canceled/{id}")
    ResponseEntity<Object> canceledSubscription(@PathVariable(value = "id") Long idSubscription);

    @PostMapping("/restarted/{id}")
    ResponseEntity<Object> restartedSubscription(@PathVariable(value = "id") Long idSubscription);
}
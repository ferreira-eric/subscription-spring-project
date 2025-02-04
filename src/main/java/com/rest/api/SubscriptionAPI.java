package com.rest.api;

import com.dtos.SubscriptionDTO;
import com.repository.entity.Subscription;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionAPI {

    @PostMapping("/create")
    ResponseEntity<Object> createSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO);

    @GetMapping("/{id}")
    ResponseEntity<Object> getSubscriptionById(@PathVariable(value = "id") UUID idSubscription);

    @GetMapping("/all")
    ResponseEntity<List<?>> getAllSubscription();

    @PostMapping("/canceled/{id}")
    ResponseEntity<Object> canceledSubscription(@PathVariable(value = "id") UUID idSubscription);

    @PostMapping("/restarted/{id}")
    ResponseEntity<Object> restartedSubscription(@PathVariable(value = "id") UUID idSubscription);
}
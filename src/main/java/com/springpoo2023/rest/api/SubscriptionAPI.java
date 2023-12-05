package com.springpoo2023.rest.api;

import com.springpoo2023.dtos.SubscriptionDTO;
import com.springpoo2023.repository.entity.Subscription;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionAPI {

    @PostMapping("/create")
    ResponseEntity<Subscription> createSubscription(@RequestBody @Valid SubscriptionDTO subscriptionDTO);

    @GetMapping("/{id}")
    ResponseEntity<Optional<Subscription>> getSubscriptionById(@PathVariable(value = "id") UUID idSubscription);

    @GetMapping("/all")
    ResponseEntity<List<Subscription>> getAllSubscription();

    @PostMapping("/canceled/{id}")
    ResponseEntity<Object> canceledSubscription(@PathVariable(value = "id") UUID idSubscription);

    @PostMapping("/restarted/{id}")
    ResponseEntity<Object> restartedSubscription(@PathVariable(value = "id") UUID idSubscription);
}
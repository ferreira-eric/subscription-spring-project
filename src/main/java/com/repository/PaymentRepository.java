package com.repository;

import com.repository.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT pay FROM Payment pay WHERE pay.subscription.id = :idSubscription")
    Optional<Payment> findBySubscriptionId(@Param("idSubscription") Long idSubscription);
}

package com.repository;

import com.repository.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT subs FROM Subscription subs WHERE subs.user.id = :idUser")
    Optional<Subscription> findByUserId(@Param("idUser") Long idUser);
}

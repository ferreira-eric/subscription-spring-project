package com.springpoo2023.repository;

import com.springpoo2023.repository.entity.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventHistoryRepository extends JpaRepository<EventHistory, UUID> {}

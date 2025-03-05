package com.repository;

import com.repository.entity.Status;
import com.utils.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status getByStatusName(StatusEnum statusName);
}

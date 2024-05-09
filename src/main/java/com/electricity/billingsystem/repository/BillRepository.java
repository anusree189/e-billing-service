package com.electricity.billingsystem.repository;

import com.electricity.billingsystem.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    BillEntity findByConsumerId(Long consumerId);

    @Query("SELECT b FROM BillEntity b WHERE b.consumerId = :consumerId ORDER BY b.id DESC LIMIT 1")
    Optional<BillEntity> findLastBillByConsumerId(Long consumerId);

    Boolean existsByConsumerIdAndDueDate(Long consumerId, LocalDate plusDays);
}
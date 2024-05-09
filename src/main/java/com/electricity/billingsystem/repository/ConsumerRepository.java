package com.electricity.billingsystem.repository;

import com.electricity.billingsystem.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Long> {
    boolean existsByConsumerNumber(Long consumerNumber);

    ConsumerEntity findByConsumerNumber(Long consumerNumber);
}
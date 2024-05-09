package com.electricity.billingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditEntity implements Serializable {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Long createdAt = Instant.now().toEpochMilli();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Long updatedAt = Instant.now().toEpochMilli();

    @PrePersist
    protected void onCreate(){
        createdAt = updatedAt = Instant.now().toEpochMilli();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now().toEpochMilli();
    }

}
package com.electricity.billingsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "bill")
public class BillEntity extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private Long billNumber;
    @NotNull
    private Long consumerId;
    @NotNull
    private Double consumedUnits;
    private Double dueAmount;
    private Short paid;
    private LocalDate dueDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consumerId", referencedColumnName = "id", insertable = false, updatable = false)
    private ConsumerEntity consumerEntity;

}

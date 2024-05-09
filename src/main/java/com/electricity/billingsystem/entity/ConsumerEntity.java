package com.electricity.billingsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "consumer")
public class ConsumerEntity extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private Long consumerNumber;
    @NotNull
    @Column(nullable = false)
    private Long slabId;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String mobileNumber;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String zip;
    private String district;
    private String state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slabId", referencedColumnName = "id", insertable = false, updatable = false)
    private SlabEntity slabEntity;
}

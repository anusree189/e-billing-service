package com.electricity.billingsystem.entity;

import com.electricity.billingsystem.enums.RateStructure;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slab")
public class SlabEntity extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    @NotNull
    private Long startDate;
    @NotNull
    private Long endDate;
    @NotNull
    private Integer unitFrom;
    @NotNull
    private Integer unitTo;
    @NotNull
    private Double tariff;
    @NotNull
    private RateStructure type;

}

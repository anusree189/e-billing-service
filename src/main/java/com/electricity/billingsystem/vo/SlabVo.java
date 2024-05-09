package com.electricity.billingsystem.vo;

import com.electricity.billingsystem.enums.RateStructure;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlabVo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull(message = "Start date cannot be null")
    private Long startDate;
    @NotNull(message = "End date cannot be null")
    private Long endDate;
    @NotNull(message = "Starting unit cannot be null")
    @Min(value = 0, message = "Unit cannot be negative)")
    private Integer unitFrom;
    @NotNull(message = "Ending unit cannot be null")
    private Integer unitTo;
    @Min(value = 0, message = "Tariff cannot be negative)")
    private Double tariff;
    private RateStructure type;
}

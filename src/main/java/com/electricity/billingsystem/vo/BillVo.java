package com.electricity.billingsystem.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillVo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long billNumber;
    @NotNull(message = "Consumer Id cannot be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long consumerId;
    @NotNull(message = "Consumer units cannot be null")
    private Double consumedUnits;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double dueAmount;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double basicCharge;
    private Short paid;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dueDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ConsumerVo consumerVo;
}

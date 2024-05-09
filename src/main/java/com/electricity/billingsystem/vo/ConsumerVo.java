package com.electricity.billingsystem.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumerVo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long consumerNumber;
    @NotNull(message = "Slab id cannot be null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long slabId;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Allowed characters for first name: A-Z and a-z")
    @NotBlank(message = "First name cannot be empty")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstName;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Allowed characters for last name: A-Z and a-z")
    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastName;
    @Pattern(regexp="(^$|[+][0-9]{1,3}[0-9]{10})" , message = "Invalid mobile number")
    @NotBlank(message = "Mobile cannot be empty")
    private String mobileNumber;
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email ID")
    private String email;
    @NotBlank(message = "Address cannot be empty")
    private String addressLine1;
    @NotBlank(message = "Address cannot be empty")
    private String addressLine2;
    @NotBlank(message = "Zip cannot be empty")
    private String zip;
    @NotBlank(message = "District cannot be empty")
    private String district;
    @NotBlank(message = "State cannot be empty")
    private String state;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ConsumerVo consumerVo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private SlabVo slabVo;

}

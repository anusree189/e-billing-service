package com.electricity.billingsystem.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    @JsonIgnore
    private HttpStatus httpStatus;
    private String status;
    private List<String> errors;

    public ApiError(HttpStatus httpStatus, String error) {
        super();
        this.httpStatus = httpStatus;
        this.errors = List.of(error);
    }

    public ApiError(String status, String error) {
        super();
        this.status = status;
        this.errors = List.of(error);
    }
}

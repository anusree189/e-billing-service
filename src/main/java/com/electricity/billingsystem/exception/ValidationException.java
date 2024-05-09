package com.electricity.billingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException{
    private final ApiError apiError;

    public ValidationException(HttpStatus httpStatus, String error) {
        super(error);
        this.apiError = new ApiError(httpStatus, error);
    }
}

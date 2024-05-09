package com.electricity.billingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException{
    private final ApiError apiError;

    public NotFoundException(String code, String error) {
        super(error);
        this.apiError = new ApiError(HttpStatus.NOT_FOUND, error);
    }
}

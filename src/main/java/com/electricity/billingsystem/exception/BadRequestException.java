package com.electricity.billingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BadRequestException extends RuntimeException{
    private final ApiError apiError;

    public BadRequestException(String error) {
        super(error);
        this.apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
    }
}

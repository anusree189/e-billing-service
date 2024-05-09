package com.electricity.billingsystem.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationError(ValidationException e) {
        ApiError errorRequest = e.getApiError();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundError(NotFoundException e) {
        ApiError errorRequest = e.getApiError();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ApiError errorRequest = ApiError.builder().errors(errors)
                .status("FAILED")
                .httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public final ResponseEntity<ApiError> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError errorRequest = ApiError.builder().errors(errors)
                .status("FAILED")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ApiError> handleNumberFormatException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Invalid " + ex.getName() + ": " + ex.getValue();
        ApiError errorRequest = ApiError.builder().errors(Collections.singletonList(errorMessage))
                .status("FAILED")
                .httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public final ResponseEntity<ApiError> handlePropertyReferenceException(PropertyReferenceException ex) {
        String errorMessage = "Cannot find property: " + ex.getPropertyName();
        ApiError errorRequest = ApiError.builder().errors(Collections.singletonList(errorMessage))
                .status("FAILED")
                .httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ApiError handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return ApiError.builder().errors(errors)
                .status("FAILED")
                .httpStatus(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiError handleIllegalArgumentException(IllegalArgumentException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return ApiError.builder().errors(errors)
                .status("FAILED")
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
        ApiError errorRequest = ApiError.builder().errors(Collections.singletonList(ex.getMessage())).build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ApiError> handleJsonErrors(HttpMessageNotReadableException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError errorRequest = ApiError.builder().errors(errors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ApiError> handleMethodExceptions(HttpRequestMethodNotSupportedException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError errorRequest = ApiError.builder().errors(errors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(getErrorMap(errorRequest), new HttpHeaders(), errorRequest.getHttpStatus());
    }

    private ApiError getErrorMap(ApiError apiErrorRequest) {
        return ApiError.builder()
                .status(apiErrorRequest.getHttpStatus().name())
                .errors(apiErrorRequest.getErrors()).build();
    }

}
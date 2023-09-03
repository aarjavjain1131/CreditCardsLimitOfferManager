package com.example.Limitoffer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.Limitoffer.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LimitOfferException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(LimitOfferException ex) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

    // Handle other custom exceptions and general exceptions here
}

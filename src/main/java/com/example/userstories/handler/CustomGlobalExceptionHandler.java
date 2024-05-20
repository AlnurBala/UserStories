package com.example.userstories.handler;

import com.example.userstories.dto.ErrorDetails;
import com.example.userstories.exception.CashBalanceNotFoundException;
import com.example.userstories.exception.InsufficientBalanceException;
import com.example.userstories.exception.OrderNotFoundException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorDetails> handleInsufficientBalanceException(InsufficientBalanceException ex, WebRequest request) {
        log.error("Insufficient balance exception", ex);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Insufficient Balance")
                .errorDetail(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CashBalanceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCashBalanceNotFoundException(CashBalanceNotFoundException ex, WebRequest request) {
        log.error("Cash balance not found exception", ex);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message("Cash Balance Not Found")
                .errorDetail(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        log.error("Order not found exception", ex);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message("Order Not Found")
                .errorDetail(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
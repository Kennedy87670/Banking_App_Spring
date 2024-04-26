package com.banking.bankingapp.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
@ResponseStatus
public class GlobalExcptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> HandleAccountNotFoundException(AccountNotFoundException exception, WebRequest request){
    LocalDateTime timeStamp = LocalDateTime.now();
    ErrorResponse message = new ErrorResponse(timeStamp, HttpStatus.NOT_FOUND, Collections.singletonList(exception.getMessage()));
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(message);
}

    @ExceptionHandler(InsufficientAccountBalance.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorResponse> HandleInsufficientAccountBalance(InsufficientAccountBalance exception, WebRequest request){
        LocalDateTime timeStamp = LocalDateTime.now();
        ErrorResponse message = new ErrorResponse(timeStamp, HttpStatus.METHOD_NOT_ALLOWED,Collections.singletonList(exception.getMessage()));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(message);
    }


}

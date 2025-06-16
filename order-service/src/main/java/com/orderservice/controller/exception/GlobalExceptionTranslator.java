package com.orderservice.controller.exception;

import com.orderservice.service.exception.OrderProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler(OrderProcessingException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleOrderProcessingException(OrderProcessingException ex) {
        return ex.getMessage();
    }
}

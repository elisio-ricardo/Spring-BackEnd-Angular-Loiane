package com.ricardo.crudspring.controller;

import com.ricardo.crudspring.exception.RecordNotFoundExcption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecordNotFoundExcption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerNotFoundException(RecordNotFoundExcption ex) {
        return ex.getMessage();
    }

}

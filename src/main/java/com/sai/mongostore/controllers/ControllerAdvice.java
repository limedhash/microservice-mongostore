package com.sai.mongostore.controllers;

import com.sai.commons.exception.NotFoundException;
import com.sai.commons.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse notFound(NotFoundException nfe){
        log.debug(nfe.getMessage());
        return new ErrorResponse(nfe.getMessage(), nfe.getItem());
    }
}

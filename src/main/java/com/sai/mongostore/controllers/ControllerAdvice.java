package com.sai.mongostore.controllers;

import com.sai.commons.exception.NotFoundException;
import com.sai.commons.exception.UnexpectedErrorException;
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
        log.info("Not found");
        log.debug(nfe.getMessage());
        return new ErrorResponse(nfe.getMessage(), nfe.getItem());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnexpectedErrorException.class)
    public ErrorResponse unexpected(UnexpectedErrorException uee){
        log.debug(uee.getMessage());
        return new ErrorResponse(uee.getMessage(), "UNEXPECTED");
    }


}

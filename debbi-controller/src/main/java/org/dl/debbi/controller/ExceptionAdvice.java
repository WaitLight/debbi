package org.dl.debbi.controller;

import lombok.extern.slf4j.Slf4j;
import org.dl.debbi.common.error.DebbiException;
import org.dl.debbi.common.error.service.ExceptionService;
import org.dl.debbi.common.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private ExceptionService exceptionService;

    @ExceptionHandler(value = DebbiException.class)
    public Response handleException(DebbiException e) {
        return Response.err(e, exceptionService.getHash(e));
    }
}

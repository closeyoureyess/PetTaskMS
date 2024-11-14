package com.pettaskmgmntsystem.PetTaskMS.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class HandlerException {

    @ExceptionHandler(MainException.class)
    protected ResponseEntity<Response> handleUserNameException(MainException e){
        log.error("Возникла ошибка: " + e.getClass() + "\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

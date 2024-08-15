package com.pettaskmgmntsystem.PetTaskMS.exeptions;

import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class HandlerExeption {

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Response> handleUserNameException(UsernameNotFoundException e){
        log.error("Возникла ошибка: " + e.getClass() + "\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServletException.class)
    protected ResponseEntity<Response> handleServletException(ServletException e){
        log.error("Возникла ошибка: " + e.getClass() + "\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll(" ", "\n"));
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Response> handleIOException(IOException e){
        log.error("Возникла ошибка, связанная с "+ e.getClass() + "\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll(" ", "\n"));
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Возникла ошибка: " + e.getClass() + "\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll(" ", "\n"));
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExecutorNotFoundExeption.class)
    protected ResponseEntity<Response> handleExecutorNotFoundExeption(ExecutorNotFoundExeption e){
        log.error("Возникла ошибка: " + e.getClass() + "\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll(" ", "\n"));
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

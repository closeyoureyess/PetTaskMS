package com.pettaskmgmntsystem.PetTaskMS.exeptions;

import com.pettaskmgmntsystem.PetTaskMS.exeptions.EntityNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExeption {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Response> handleExeption(UsernameNotFoundException e ){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.czh.controller;

import com.czh.entity.Error;
import com.czh.exception.DatabaseException;
import com.czh.exception.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExceptionController {
    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error loginError(LoginException e){
        return new Error(400, e.getMessage());
    }

    @RequestMapping(value = "/error/{msg}")
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Error signError(@PathVariable String msg){

        return new Error(406, msg);
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DatabaseException DatabaseOperationError(DatabaseException e) {
        return e;
    }
}

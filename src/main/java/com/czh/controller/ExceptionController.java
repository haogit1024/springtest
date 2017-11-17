package com.czh.controller;

import com.czh.entity.Error;
import com.czh.exception.DatabaseException;
import com.czh.exception.LoginException;
import com.czh.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DatabaseException DatabaseOperationError(DatabaseException e) {
        return e;
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error loginError(LoginException e){
        return new Error(400, e.getMsg());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error notFound(NotFoundException e){
        return new Error(404, e.getId() + " NOT_FOUND");
    }
}

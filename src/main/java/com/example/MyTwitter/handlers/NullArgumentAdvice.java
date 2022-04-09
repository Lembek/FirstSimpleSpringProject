package com.example.MyTwitter.handlers;

import com.example.MyTwitter.exceptions.NullArgumentException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NullArgumentAdvice {

    @ExceptionHandler(NullArgumentException.class)
    public String nullArgumentHandler(NullArgumentException e) {
        return e.getMessage();
    }
}

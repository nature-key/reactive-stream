package com.jiepi.reactive.advice;

import com.jiepi.reactive.exception.StudentExcepption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
public class StudentAdvice {

    @ExceptionHandler
    public ResponseEntity<String> validateHandle(WebExchangeBindException ex) {
        return new ResponseEntity<String>(exStr(ex), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<String> validateHandle(StudentExcepption se) {
        String errMessage = se.getMessage() + ":【" + se.getErrField() + ":" + se.getErrValue() + "】";
        return new ResponseEntity<String>(errMessage, HttpStatus.BAD_REQUEST);
    }

    private String exStr(WebExchangeBindException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }


}

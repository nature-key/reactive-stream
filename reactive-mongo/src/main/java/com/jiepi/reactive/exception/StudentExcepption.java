package com.jiepi.reactive.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentExcepption extends RuntimeException {

    private String errField;

    private String errValue;

    public StudentExcepption(String message, String errField, String errValue) {
        super(message);
        this.errField = errField;
        this.errValue = errValue;
    }
}

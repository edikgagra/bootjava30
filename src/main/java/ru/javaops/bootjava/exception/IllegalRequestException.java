package ru.javaops.bootjava.exception;

import org.springframework.http.HttpStatus;

public class IllegalRequestException extends ExceptionApplication {
    public IllegalRequestException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg);
    }
}
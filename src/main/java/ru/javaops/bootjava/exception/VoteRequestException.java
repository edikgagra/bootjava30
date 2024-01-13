package ru.javaops.bootjava.exception;

import org.springframework.http.HttpStatus;

public class VoteRequestException extends ExceptionApplication {
    public VoteRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

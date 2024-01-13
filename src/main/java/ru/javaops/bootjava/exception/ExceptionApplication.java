package ru.javaops.bootjava.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNullApi;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ExceptionApplication extends ResponseStatusException {

    public ExceptionApplication(HttpStatus status, String message) {
        super(status, message);
    }

    @Override
    public String getMessage() {
        return getReason();
    }
}

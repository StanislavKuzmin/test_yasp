package com.github.stanislav.kuzmin.yasp.error;

import org.springframework.http.HttpStatus;

public enum ErrorType {

    APP_ERROR("Application error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Bad request", HttpStatus.UNPROCESSABLE_ENTITY);

    ErrorType(String title, HttpStatus status) {
        this.title = title;
        this.status = status;
    }

    public final String title;
    public final HttpStatus status;
}

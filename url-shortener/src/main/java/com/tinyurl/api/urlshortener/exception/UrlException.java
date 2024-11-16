package com.tinyurl.api.urlshortener.exception;

import org.springframework.http.HttpStatus;

public class UrlException extends RuntimeException {

    private final HttpStatus status;

    public UrlException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}


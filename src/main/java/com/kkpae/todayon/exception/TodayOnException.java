package com.kkpae.todayon.exception;

import org.springframework.http.HttpStatus;

public class TodayOnException extends RuntimeException {
    private final TodayOnExceptionResponse response;

    public TodayOnException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.response = new TodayOnExceptionResponse(msg, httpStatus);
    }

    public TodayOnExceptionResponse getResponse() {
        return response;
    }
}

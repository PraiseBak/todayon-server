package com.kkpae.todayon.exception;

import org.springframework.http.HttpStatus;

public record TodayOnExceptionResponse(String msg, HttpStatus httpStatus){
}

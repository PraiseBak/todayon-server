package com.kkpae.todayon.exception;

public enum TodayOnExceptionMessage {
    LOGIN_USERNAME_INVALID("유효하지 않은 아이디입니다"),
    USERNAME_DUPLICATE("중복된 아이디입니다."),
    LOGIN_PASSWORD_INVALID("유효하지 않은 비밀번호입니다")
    ;

    private final String msg;

    TodayOnExceptionMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

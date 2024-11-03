package com.kkpae.todayon.utility;

import java.time.LocalDateTime;

public class DateTimeUtility {

    public static LocalDateTime expireDateTime() {
        return LocalDateTime.now().plusMonths(1);
    }
}

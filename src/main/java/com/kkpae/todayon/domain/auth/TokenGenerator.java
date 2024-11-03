package com.kkpae.todayon.domain.auth;

import java.util.UUID;

public class TokenGenerator {

    public static String sessionToken() {
        // UUID를 활용하여 고유한 세션 토큰 생성
        return UUID.randomUUID().toString();
    }
}

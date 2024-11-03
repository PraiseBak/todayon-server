package com.kkpae.todayon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praiseutil.timelog.utility.LogTrace;
import com.praiseutil.timelog.utility.TraceLocalLogTrace;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppConfig {

    @Bean
    public LogTrace logTrace() {
        return new TraceLocalLogTrace();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            // 세션 ID를 생성하고 응답
            HttpSession session = request.getSession();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            responseData.put("email", userDetails.getUsername());
            // 필요한 사용자 정보 추가

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
        };
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", "Authentication failed");
            responseData.put("message", exception.getMessage());

            response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
        };
    }
}
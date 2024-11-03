package com.kkpae.todayon.controller;

import com.kkpae.todayon.dto.RequestLogin;
import com.kkpae.todayon.dto.RequestSignup;
import com.kkpae.todayon.service.LoginSessionResponse;
import com.kkpae.todayon.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "TodayGoalController", description = "오늘의 목표와 관련된 기능을 요청하는 api입니다")
public class AuthController {

    private final UserService userService; // 사용자 서비스

    @PostMapping("/login")
    @Operation(summary = "로그인합니다", description = "로그인하기 위해 id와 password를 제출합니다")
    public ResponseEntity<?> requestLogin(
            HttpServletRequest request,
            @RequestBody RequestLogin requestLogin) {
        HttpSession session = request.getSession();
        LoginSessionResponse loginResponse = userService.login(requestLogin);
        session.setAttribute("SESSION_TOKEN", loginResponse.sessionToken());
        session.setAttribute("USER_INFO", loginResponse.username());
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입합니다", description = "회원가입을 위해 카테고리와 닉네임 유저네임 패스워드가 필요합니다")
    public ResponseEntity<?> signup(
            HttpServletRequest request,
            @RequestBody RequestSignup requestSignup) {
        userService.signup(requestSignup);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}

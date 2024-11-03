package com.kkpae.todayon.controller;

import com.kkpae.todayon.dto.ObjectRequest;
import com.kkpae.todayon.service.GoalService;
import com.kkpae.todayon.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todayGoal")
@RequiredArgsConstructor
@Tag(name = "TodayGoalController", description = "오늘의 목표와 관련된 기능을 요청하는 api입니다")
public class TodayGoalController {

    private final UserService userService; // 사용자 서비스
    private final GoalService goalService; // Object 처리 서비스

    @PostMapping("/register")
    @Operation(summary = "목표를 등록합니다", description = "목표를 등록하기 위해 목표 설명 및 이미지를 제출해야합니다")
    public ResponseEntity<?> registerObject(
            @AuthenticationPrincipal User user,
            @RequestBody ObjectRequest objectRequest) {
        goalService.registerObject(user, objectRequest); // 사용자와 ObjectRequest를 저장하는 서비스 메서드 호출
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}

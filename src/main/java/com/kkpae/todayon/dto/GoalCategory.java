package com.kkpae.todayon.dto;

import lombok.Getter;

@Getter
public enum GoalCategory {
    WORK("운동"),
    SELF_DEVELOPMENT("자기계발"),
    STUDY("공부"),
    LIFE("라이프")
    ;

    private final String goalCategory;

    GoalCategory(String goalCategory) {
        this.goalCategory = goalCategory;
    }
}

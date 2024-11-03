package com.kkpae.todayon.dto;

import java.util.List;

public record RequestSignup(String username, String password, List<GoalCategory> goalCategoryList, String nickname) {
}

package com.kkpae.todayon.repository;

import com.kkpae.todayon.domain.TodayGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<TodayGoal,Long> {
}

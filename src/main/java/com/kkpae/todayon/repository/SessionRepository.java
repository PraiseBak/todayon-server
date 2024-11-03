package com.kkpae.todayon.repository;

import com.kkpae.todayon.domain.LoginSession;
import com.kkpae.todayon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<LoginSession,Long> {
    LoginSession findBySessionToken(String sessionToken);

    LoginSession findByUser(User user);
}

package com.kkpae.todayon.repository;

import com.kkpae.todayon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    boolean existsByNickname(String nickname);
    boolean existsByUsername(String username);

}

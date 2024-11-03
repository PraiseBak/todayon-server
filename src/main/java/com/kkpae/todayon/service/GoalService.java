package com.kkpae.todayon.service;

import com.kkpae.todayon.dto.ObjectRequest;
import com.kkpae.todayon.repository.ObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final ObjectRepository rvobjectrepository;

    public void registerObject(User user, ObjectRequest objectRequest) {

    }
}

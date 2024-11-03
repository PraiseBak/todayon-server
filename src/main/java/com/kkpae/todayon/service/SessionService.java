package com.kkpae.todayon.service;

import com.kkpae.todayon.domain.LoginSession;
import com.kkpae.todayon.domain.User;
import com.kkpae.todayon.repository.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService{
    private final SessionRepository sessionRepository;

    public LoginSession findByLoginSessionToken(String sessionToken){
        return sessionRepository.findBySessionToken(sessionToken);
    }

    @Transactional
    public LoginSessionResponse createNewSession(User user){
        LoginSession loginSession = sessionRepository.findByUser(user);
        LoginSession newSession = LoginSession.createLoginSession(user);
        if(loginSession == null){
            sessionRepository.save(newSession);
        }
        loginSession = newSession;
        return new LoginSessionResponse(user.getUsername(),loginSession.getSessionToken());
    }

    public LoginSession getValidLoginSessionByToken(String sessionToken) {
        LoginSession loginSession = sessionRepository.findBySessionToken(sessionToken);
        if(loginSession == null){
            return null;
        }
        if(loginSession.isExpiredSession()){
            return null;
        }
        return loginSession;

    }

    @Transactional
    public LoginSession update(LoginSession newLoginSession) {
        LoginSession prevLoginSession = sessionRepository.findByUser(newLoginSession.getUser());
        prevLoginSession.update(newLoginSession);
        return prevLoginSession;
    }
}

package com.kkpae.todayon.service;

import com.kkpae.todayon.domain.LoginSession;
import com.kkpae.todayon.domain.Role;
import com.kkpae.todayon.domain.User;
import com.kkpae.todayon.dto.RequestLogin;
import com.kkpae.todayon.dto.RequestSignup;
import com.kkpae.todayon.exception.TodayOnException;
import com.kkpae.todayon.exception.TodayOnExceptionMessage;
import com.kkpae.todayon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean isExistsUserByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean isExistsUserByNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    public void signup(RequestSignup requestSignup) {
        if(isExistsUserByUsername(requestSignup.username())){
            throw new TodayOnException(TodayOnExceptionMessage.USERNAME_DUPLICATE.getMsg(), HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .role(Role.USER_ROLE)
                .username(requestSignup.username())
                .password(passwordEncoder.encode(requestSignup.password()))
                .nickname(requestSignup.nickname())
                .goalCategorys(requestSignup.goalCategoryList())
                .build();
        userRepository.save(user);
    }

    public User getUserBySessionToken(String sessionToken) {
        LoginSession loginSession = sessionService.getValidLoginSessionByToken(sessionToken);
        return loginSession.getUser();
    }

    public LoginSessionResponse login(RequestLogin requestLogin) {
        User user = loadUserByUsername(requestLogin.username());
        if(user == null){
            throw new TodayOnException(TodayOnExceptionMessage.LOGIN_USERNAME_INVALID.getMsg(), HttpStatus.BAD_REQUEST);
        }
        if(!passwordEncoder.matches(requestLogin.password(), user.getPassword())){
            throw new TodayOnException(TodayOnExceptionMessage.LOGIN_PASSWORD_INVALID.getMsg(), HttpStatus.BAD_REQUEST);
        }
        LoginSession loginSession = LoginSession.createLoginSession(user);
        sessionService.update(loginSession);
        return new LoginSessionResponse(user.getUsername(),loginSession.getSessionToken());
    }
}

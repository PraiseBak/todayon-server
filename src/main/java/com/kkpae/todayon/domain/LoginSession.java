package com.kkpae.todayon.domain;

import com.kkpae.todayon.domain.auth.TokenGenerator;
import com.kkpae.todayon.utility.DateTimeUtility;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginSession extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Getter
    private User user;

    @Getter
    private String sessionToken;

    @Getter
    private LocalDateTime expireDateTime;

    public boolean isExpiredSession(){
        LocalDateTime cur = LocalDateTime.now();
        if(cur.getSecond() > expireDateTime.getSecond()){
            return true;
        }
        return false;
    }

    public static LoginSession createLoginSession(User user){
        return LoginSession.builder()
                .sessionToken(TokenGenerator.sessionToken())
                .user(user)
                .expireDateTime(DateTimeUtility.expireDateTime())
                .build();
    }

    public void update(LoginSession newSession){
        this.sessionToken = newSession.getSessionToken();
        this.expireDateTime = newSession.getExpireDateTime();
    }
}

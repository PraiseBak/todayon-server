package com.kkpae.todayon.domain;

import com.kkpae.todayon.dto.GoalCategory;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 8, message = "닉네임은 4자 이상 8자 이하이어야 합니다.")
    @Pattern(regexp = "^[^\\W_]+$", message = "닉네임에는 특수문자를 사용할 수 없습니다.")
    private String nickname;

    @Size(min = 4, max = 4, message = "사용자는 4자여야 합니다.")
    private String username;

    @Size(min = 4, message = "비밀번호는 4자 이상이어야 합니다.")
    private String password;

    @Valid
    @Size(min = 1, message = "골 카테고리는 1개 이상이어야 합니다.")
    @ElementCollection
    private List<GoalCategory> goalCategorys;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER_ROLE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRole());
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

package likelion.portmate.domain.member.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.member.controller.exception.LoginFailedException;
import likelion.portmate.global.auth.service.dto.AuthAttributes;
import likelion.portmate.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    private String externalId;

    @Enumerated(STRING)
    private LoginProvider loginProvider;

    @Builder
    private Member(
            String loginId,
            String email,
            String password,
            String username,
            String externalId,
            LoginProvider loginProvider
    ) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.externalId = externalId;
        this.loginProvider = loginProvider;
    }

    public void checkPassword(PasswordEncoder passwordEncoder, String password) {
        if (!passwordEncoder.matches(password, this.password)) {
            throw new LoginFailedException();
        }
    }

    public static Member from(AuthAttributes authAttributes) {
        return Member.builder()
                .email(authAttributes.getEmail())
                .externalId(authAttributes.getExternalId())
                .loginProvider(authAttributes.getProvider())
                .build();
    }

    public boolean hasDifferentProviderWithEmail(String email, String externalId) {
        return Objects.equals(this.email, email) && !Objects.equals(this.externalId, externalId);
    }

}
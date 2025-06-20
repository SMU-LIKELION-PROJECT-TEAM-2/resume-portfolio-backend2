package likelion.portmate.global.auth.entity;

import jakarta.persistence.*;
import likelion.portmate.global.auth.controller.exception.RefreshTokenNotValidException;
import likelion.portmate.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    private Long memberId;

    private String token;

    private LocalDateTime expiredAt;

    private RefreshToken(Long memberId, String token, LocalDateTime expiredAt) {
        this.memberId = memberId;
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public static RefreshToken of(Long memberId, String token, long expiredSeconds) {
        LocalDateTime expiredAt = LocalDateTime.now().plusSeconds(expiredSeconds);

        return new RefreshToken(memberId, token, expiredAt);
    }

    public void rotate(String token) {
        this.token = token;
    }

    public void updateExpirationIfExpired(long expiredSeconds) {
        if (isExpired()) {
            expiredAt = LocalDateTime.now().plusSeconds(expiredSeconds);
        }
    }

    public void validateWith(String token, Long memberId) {
        if (isNotMatchedToken(token) || isExpired() || isNotMatchedUserId(memberId)) {
            throw new RefreshTokenNotValidException();
        }
    }

    private boolean isNotMatchedToken(String token) {
        return !Objects.equals(this.token, token);
    }

    private boolean isExpired() {
        return expiredAt.isBefore(LocalDateTime.now());
    }

    private boolean isNotMatchedUserId(Long memberId) {
        return !Objects.equals(this.memberId, memberId);
    }

}

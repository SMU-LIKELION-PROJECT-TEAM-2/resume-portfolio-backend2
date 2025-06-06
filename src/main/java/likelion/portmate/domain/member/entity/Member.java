package likelion.portmate.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id", nullable = true, unique = true)
    private String loginId; // 로그인용 ID

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String password; // 카카오 로그인은 NULL 허용

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType socialType;

    @Column(name = "kakao_id", nullable = true, unique = true)
    private String kakaoId;

    @Column(nullable = true)
    private String refreshToken;

    @Builder
    public Member(String loginId, String email, String password, String nickname, SocialType socialType, String kakaoId, String refreshToken) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.socialType = socialType;
        this.kakaoId = kakaoId;
        this.refreshToken = refreshToken;
    }

    public static Member createLocal(String loginId, String email, String password, String nickname) {
        return Member.builder()
                .loginId(loginId)
                .email(email)
                .password(password)
                .nickname(nickname)
                .socialType(SocialType.LOCAL)
                .build();
    }

    public static Member createKakao(String email, String nickname, String kakaoId) {
        return Member.builder()
                .loginId("kakao_" + kakaoId)
                .email(email)
                .password(null)
                .nickname(nickname)
                .socialType(SocialType.KAKAO)
                .kakaoId(kakaoId)
                .refreshToken(null)
                .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
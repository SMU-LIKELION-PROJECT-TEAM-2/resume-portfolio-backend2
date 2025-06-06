package likelion.portmate.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoLoginDto {

    private TokenResponseDto token;
    private String nickname;
}
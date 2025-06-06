package likelion.portmate.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    private String loginId;
    private String email;
    private String password;
    private String nickname;
}
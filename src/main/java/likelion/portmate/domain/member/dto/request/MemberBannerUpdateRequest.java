package likelion.portmate.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberBannerUpdateRequest(
        @NotBlank(message = "배너 이미지 URL은 필수입니다.")
        String bannerImageUrl
) {
}
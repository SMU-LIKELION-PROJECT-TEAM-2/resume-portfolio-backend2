package likelion.portmate.domain.member.entity;

import java.util.Objects;

public enum LoginProvider {
    kakao;

    public boolean isProviderOf(String providerId) {
        return Objects.equals(this.name(), providerId);
    }
}

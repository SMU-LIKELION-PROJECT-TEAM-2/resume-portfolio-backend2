package likelion.portmate.domain.career.entity;

import lombok.Getter;

@Getter
public enum WorkType {
    REGULAR("정규직"),
    CONTRACT("계약직"),
    INTERN("인턴"),
    FREELANCER("프리랜서");

    private final String displayName;

    WorkType(String displayName) {
        this.displayName = displayName;
    }

}


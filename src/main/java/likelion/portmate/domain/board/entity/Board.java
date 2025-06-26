package likelion.portmate.domain.board.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.member.entity.Member;
import likelion.portmate.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(STRING)
    @Column(nullable = false)
    private BoardCategory boardCategory;

    @Enumerated(STRING)
    @Column(nullable = false)
    private DepartmentCategory departmentCategory;

    @Builder
    private Board(
            Member member,
            String title,
            String content,
            BoardCategory boardCategory,
            DepartmentCategory departmentCategory
    ) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.boardCategory = boardCategory;
        this.departmentCategory = departmentCategory;
    }
}

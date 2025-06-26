package likelion.portmate.domain.job.entity;

import jakarta.persistence.*;
import likelion.portmate.domain.member.entity.DepartmentCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;

    @Enumerated(STRING)
    private DepartmentCategory departmentCategory;

}

package likelion.portmate.domain.resume.repository;

import likelion.portmate.domain.resume.entity.Resume;
import likelion.portmate.domain.resume.entity.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResumeRepositoryImpl implements ResumeRepository {

    private final ResumeJpaRepository resumeJpaRepository;

    @Override
    public void save(Resume resume) {
        resumeJpaRepository.save(resume);
    }
}

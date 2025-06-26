package likelion.portmate.domain.interestfield.repository;

import likelion.portmate.domain.interestfield.controller.exception.InterestFieldNotFoundException;
import likelion.portmate.domain.interestfield.dto.response.InterestFieldInfoResponse;
import likelion.portmate.domain.interestfield.entity.InterestField;
import likelion.portmate.domain.interestfield.entity.InterestFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class InterestFieldRepositoryImpl implements InterestFieldRepository {

    private final InterestFieldJpaRepository interestFieldJpaRepository;
    private final InterestFieldQueryDslRepository queryDslRepository;

    @Override
    public List<InterestFieldInfoResponse> findAllByInterestFieldContaining(List<String> keywords) {
        return queryDslRepository.findAllByInterestFieldContaining(keywords);
    }

    @Override
    public InterestField findByInterestFieldId(Long interestFieldId) {
        return interestFieldJpaRepository.findById(interestFieldId)
                .orElseThrow(InterestFieldNotFoundException::new);
    }

}

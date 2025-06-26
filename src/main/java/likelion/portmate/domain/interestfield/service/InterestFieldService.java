package likelion.portmate.domain.interestfield.service;

import likelion.portmate.domain.interestfield.dto.response.InterestFieldInfoResponse;
import likelion.portmate.domain.interestfield.entity.InterestFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterestFieldService {

    private final InterestFieldRepository interestFieldRepository;

    @Transactional(readOnly = true)
    public List<InterestFieldInfoResponse> findAllByInterestFieldContaining(List<String> keywords) {
        return interestFieldRepository.findAllByInterestFieldContaining(keywords);
    }

}

package likelion.portmate.domain.interestfield.entity;

import likelion.portmate.domain.interestfield.dto.response.InterestFieldInfoResponse;

import java.util.List;

public interface InterestFieldRepository {

    List<InterestFieldInfoResponse> findAllByInterestFieldContaining(List<String> keywords);

    InterestField findByInterestFieldId(Long interestFieldId);
}

package likelion.portmate.domain.interestfield.controller;

import likelion.portmate.domain.interestfield.dto.response.InterestFieldInfoResponse;
import likelion.portmate.domain.interestfield.service.InterestFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/interest-fields")
@RestController
public class InterestFieldController implements InterestFieldDocsController {

    private final InterestFieldService interestFieldService;

    @GetMapping("/search")
    public ResponseEntity<List<InterestFieldInfoResponse>> findAllByInterestFieldContaining(
            @RequestParam List<String> keywords
    ) {
        List<InterestFieldInfoResponse> response = interestFieldService.findAllByInterestFieldContaining(keywords);
        return ResponseEntity.ok(response);
    }

}

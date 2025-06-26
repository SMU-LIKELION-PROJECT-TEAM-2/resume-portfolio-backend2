package likelion.portmate.domain.resume.controller;

import likelion.portmate.domain.resume.dto.request.ResumeSaveRequest;
import likelion.portmate.domain.resume.service.ResumeService;
import likelion.portmate.global.annotation.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/resumes")
@RestController
public class ResumeController implements ResumeDocsController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<Void> save(
            @MemberId Long memberId,
            @RequestBody ResumeSaveRequest request
    ) {
        resumeService.save(memberId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

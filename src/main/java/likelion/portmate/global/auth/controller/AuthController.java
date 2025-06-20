package likelion.portmate.global.auth.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import likelion.portmate.global.auth.dto.request.LoginResultRequest;
import likelion.portmate.global.auth.dto.response.LoginResultResponse;
import likelion.portmate.global.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/login")
@Controller
public class AuthController implements AuthDocsController{

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResultResponse> login(
            @Valid @RequestBody LoginResultRequest request
    ) {
        LoginResultResponse result = authService.login(
                request.loginId(),
                request.password()
        );

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public String login() {
        return "redirect:/oauth2/authorization/kakao";
    }

}

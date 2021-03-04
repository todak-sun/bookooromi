package io.todak.bookooromi.auth;

import io.todak.bookooromi.auth.dto.LoginRequest;
import io.todak.bookooromi.auth.dto.TokenResponse;
import io.todak.bookooromi.common.entities.network.Response;
import io.todak.bookooromi.security.jwt.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> authorize(@RequestBody @Valid LoginRequest request) {
        TokenResponse tokenResponse = authService.createTokenResponse(request.getUsername(), request.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtRequestFilter.AUTHORIZATION_HEADER, "Bearer " + tokenResponse.getToken());

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(Response.builder()
                        .data(tokenResponse)
                        .build());
    }

}

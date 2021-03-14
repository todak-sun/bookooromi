package io.todak.bookooromi.auth;

import io.todak.bookooromi.domain.Account;
import io.todak.bookooromi.account.AccountService;
import io.todak.bookooromi.auth.dto.TokenResponse;
import io.todak.bookooromi.security.jwt.TokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    AuthService authService;

    @Test
    @DisplayName("TokenResponse를 생성하는 테스트")
    public void createTokenResponse() {
        //given
        String username = "todaksun@gmail.com";
        String password = "password";

        Account account = accountService.signUp(Account.builder()
                .username(username)
                .password(password)
                .build());

        //when
        TokenResponse tokenResponse = authService.createTokenResponse(username, password);

        //then
        assertNotNull(tokenResponse.getToken());
        assertEquals(account.getUsername(), tokenProvider.getUsername(tokenResponse.getToken()));
    }

}
package io.todak.bookooromi.auth;

import io.todak.bookooromi.domain.Account;
import io.todak.bookooromi.account.AccountService;
import io.todak.bookooromi.auth.dto.LoginRequest;
import io.todak.bookooromi.common.ApiTest;
import io.todak.bookooromi.domain.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest extends ApiTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("성공적으로 로그인하는 테스트")
    public void loginTest() throws Exception {

        accountRepository.deleteAll();

        //given
        String username = "todaksun@gmail.com";
        String password = "password";

        accountService.signUp(Account.builder()
                .username(username)
                .password(password)
                .build());

        String json = objectMapper.writeValueAsString(LoginRequest.builder()
                .username(username)
                .password(password)
                .build());

        //when
        ResultActions actions = mvc.perform(post("/api/auth/login")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.transactionTime").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.token").exists())
        ;

    }

}
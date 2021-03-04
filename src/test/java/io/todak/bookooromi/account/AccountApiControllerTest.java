package io.todak.bookooromi.account;

import io.todak.bookooromi.account.dto.SignUpRequest;
import io.todak.bookooromi.common.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AccountApiControllerTest extends ApiTest {

    @Test
    @DisplayName("회원가입을 진행하는 api")
    public void sign_up() throws Exception {
        //given
        String username = "username";
        String password = "password";
        SignUpRequest request = new SignUpRequest(username, password, password);

        String json = objectMapper.writeValueAsString(request);

        //when
        ResultActions actions = mvc.perform(
                post("/api/sign-up")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.enrolledAt").exists())
                .andExpect(jsonPath("$.data.username").exists())
                .andExpect(jsonPath("$.data.password").doesNotExist())
                .andExpect(jsonPath("$.transactionTime").exists())
        ;

    }

}

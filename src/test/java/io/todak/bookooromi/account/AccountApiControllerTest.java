package io.todak.bookooromi.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.todak.bookooromi.account.dto.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@ActiveProfiles("test")
class AccountApiControllerTest {

    @Autowired
    WebApplicationContext ctx;
    @Autowired
    ObjectMapper objectMapper;

    MockMvc mvc;

    @BeforeEach
    public void setUpMockMvc() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8"))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("회원가입을 진행하는 api")
    public void sign_up() throws Exception {
        //given
        String username = "username";
        String password = "password";
        SignUpRequest request = new SignUpRequest(username, password, password);

        String json = objectMapper.writeValueAsString(request);

        System.out.println(">>>>>>>>>>>>>>>> json : " + json);


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

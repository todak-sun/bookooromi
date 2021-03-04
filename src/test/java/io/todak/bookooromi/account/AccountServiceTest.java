package io.todak.bookooromi.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("회원가입을 정상적으로 통과하는 테스트")
    public void sign_up_test() {
        //given
        String username = "todaksun@gmail.com";
        String password = "password";

        Account account = Account.builder()
                .username(username)
                .password(password)
                .build();

        //when
        Account newAccount = accountService.signUp(account);

        //then
        assertEquals(username, newAccount.getUsername());
        assertNotEquals(password, newAccount.getPassword());
        assertNotNull(newAccount.getId());
        assertTrue(newAccount.isActivated());
    }


}
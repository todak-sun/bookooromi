package io.todak.bookooromi.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
class AccountTests {

    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Test
    @DisplayName("빌더가 제대로 동작하는지 확인")
    public void test() {
        Account account = Account.builder().build();
        assertNotNull(account);
    }

    @Test
    @DisplayName("password를 인코딩하는지 검사")
    public void password_encode() {
        //given
        String password = "password";

        Account account = Account.builder()
                .username("todaksun@gmail.com")
                .password(password)
                .build();

        //when
        account.encodePassword(encoder);

        //then
        assertNotEquals(password, account.getPassword());


    }
}
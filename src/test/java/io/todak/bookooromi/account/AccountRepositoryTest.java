package io.todak.bookooromi.account;

import io.todak.bookooromi.config.JpaConfigurer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@ActiveProfiles("test")
@Import(JpaConfigurer.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("bean 으로 등록 되어있는지 확인")
    public void is_bean() {
        assertNotNull(accountRepository);
    }

    @Test
    @DisplayName("account 저장시 생성일시 정보도 함께 저장")
    public void save_with_datetime() {
        //given
        String username = "todaksun@gmail.com";
        String password = "password";
        Account newAccount = Account.builder()
                .username(username)
                .password(password)
                .build();

        //when
        Account savedAccount = accountRepository.save(newAccount);

        //then
        assertEquals(savedAccount.getUsername(), username);
        assertEquals(savedAccount.getPassword(), password);
        assertNotNull(savedAccount.getId());
        assertNotNull(savedAccount.getCreatedDateTime());
        assertNotNull(savedAccount.getUpdatedDateTime());
    }

}
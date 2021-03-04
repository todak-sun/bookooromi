package io.todak.bookooromi.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QueryDSL - AccountRepository 테스트")
@ActiveProfiles("test")
@SpringBootTest
class AccountQRepositoryTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountQRepository accountQRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("빈으로 등록이 되어있다.")
    public void is_bean() {
        assertNotNull(accountQRepository);
    }

    @Test
    @DisplayName("저장한 엔티티가, QueryDSL을 통해 잘 조회가 되는지 테스트")
    public void find_by_id() {
        //given
        String username = "todaksun@gmail.com";
        String password = "password";
        Account savedAccount = saveAccount(username, password);

        //when
        Optional<Account> optionalAccount = accountQRepository.findById(savedAccount.getId());

        //then
        if (optionalAccount.isEmpty()) {
            fail();
        }
        Account fetchedAccount = optionalAccount.get();

        assertEquals(savedAccount.getId(), fetchedAccount.getId());
        assertEquals(savedAccount.getUsername(), fetchedAccount.getUsername());
        assertNotEquals(password, fetchedAccount.getPassword());
    }

    private Account saveAccount(String username, String password) {
        return accountService.signUp(Account.builder()
                .username(username)
                .password(password)
                .build());
    }

    @Test
    @Transactional
    public void update_test() {
        //given
        String username = "todaksun@gmail.com";
        String password = "password";
        Account savedAccount = saveAccount(username, password);

        String hashedPasswordBefore = savedAccount.getPassword();

        Long savedId = savedAccount.getId();
        em.flush();
        em.clear();

        Account beforeUpdate = accountQRepository.findById(savedId).get();
        beforeUpdate.changePassword(passwordEncoder, "String");
        em.flush();
        em.clear();

        Account afterUpdate = accountQRepository.findById(savedId).get();
        String hashedPasswordAfter = afterUpdate.getPassword();

        assertNotEquals(hashedPasswordAfter, hashedPasswordBefore);
    }

}
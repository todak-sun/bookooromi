package io.todak.bookooromi.account;

import io.todak.bookooromi.account.dto.SignUpRequest;
import io.todak.bookooromi.account.dto.SignUpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@DataJpaTest
@ActiveProfiles("test")
class AccountMapperTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("SignUpRequest -> Account")
    public void toEntityFromSignUpRequest() {
        //given
        String username = "username";
        String password = "password";
        String passwordRe = "passwordRe";
        SignUpRequest request = new SignUpRequest(username, password, passwordRe);

        //when
        Account account = AccountMapper.INSTANCE.toEntity(request);

        //then
        assertNotNull(account);
        assertEquals(username, account.getUsername());
        assertEquals(password, account.getPassword());
    }

    @Test
    @DisplayName("Account -> SignUpResponse")
    public void toSignUpResponse() {
        //given
        String username = "username";
        String password = "password";

        Account saveAccount = Account.builder()
                .username(username)
                .password(password)
                .build();

        Account newAccount = accountRepository.save(saveAccount);

        //when
        SignUpResponse signUpResponse = AccountMapper.INSTANCE.toSignUpResponse(newAccount);

        //then
        assertEquals(username, signUpResponse.getUsername());
        assertEquals(newAccount.getId(), signUpResponse.getId());
        assertEquals(newAccount.getCreatedDateTime(), signUpResponse.getEnrolledAt());

    }


}
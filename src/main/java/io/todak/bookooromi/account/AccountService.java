package io.todak.bookooromi.account;

import io.todak.bookooromi.domain.Account;
import io.todak.bookooromi.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account signUp(Account account) {
        account.encodePassword(passwordEncoder);
        account.activate();
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }


}

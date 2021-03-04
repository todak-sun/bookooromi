package io.todak.bookooromi.security;

import io.todak.bookooromi.account.Account;
import io.todak.bookooromi.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SecurityUserService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findOneWithAuthoritiesByUsername(email)
                .map(account -> createUser(email, account))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(email);
                });
    }

    private User createUser(String username, Account account) {
        if (!account.isActivated()) {
            //TODO: 에러처리
            throw new RuntimeException(username + " -> 비활성화");
        }
        List<SimpleGrantedAuthority> authorities = account.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.name()))
                .collect(Collectors.toList());
        return new User(account.getUsername(), account.getPassword(), authorities);
    }
}

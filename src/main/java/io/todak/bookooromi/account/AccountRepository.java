package io.todak.bookooromi.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneWithAuthoritiesByUsername(String email);

}

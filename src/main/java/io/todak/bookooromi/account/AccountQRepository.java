package io.todak.bookooromi.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.todak.bookooromi.domain.Account;
import io.todak.bookooromi.domain.QAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class AccountQRepository {

    private final EntityManager em;
    private JPAQueryFactory qf;

    @PostConstruct
    private void setUp() {
        this.qf = new JPAQueryFactory(em);
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(
                qf.select(QAccount.account).from(QAccount.account).where(QAccount.account.id.eq(id)).fetchOne()
        );
    }

}

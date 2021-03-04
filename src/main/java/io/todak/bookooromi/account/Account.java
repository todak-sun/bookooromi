package io.todak.bookooromi.account;

import io.todak.bookooromi.account.authority.Authority;
import io.todak.bookooromi.common.entities.BaseDateTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@AttributeOverrides({
        @AttributeOverride(name = "createdDateTime", column = @Column(name = "enrolled_date_time"))
})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_account")
@Entity
public class Account extends BaseDateTimeEntity {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Authority> authorities;


    @Builder
    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void activate() {
        this.activated = true;
    }

    public void deactivate() {
        this.activated = false;
    }
}

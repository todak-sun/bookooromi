package io.todak.bookooromi.account;

import io.todak.bookooromi.account.Account.AccountBuilder;
import io.todak.bookooromi.account.dto.SignUpRequest;
import io.todak.bookooromi.account.dto.SignUpResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-01T22:02:59+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.6 (Oracle Corporation)"
)
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(SignUpRequest signUpRequest) {
        if ( signUpRequest == null ) {
            return null;
        }

        AccountBuilder account = Account.builder();

        account.username( signUpRequest.getUsername() );
        account.password( signUpRequest.getPassword() );

        return account.build();
    }

    @Override
    public SignUpResponse toSignUpResponse(Account account) {
        if ( account == null ) {
            return null;
        }

        LocalDateTime enrolledAt = null;
        Long id = null;
        String username = null;

        enrolledAt = account.getCreatedDateTime();
        id = account.getId();
        username = account.getUsername();

        SignUpResponse signUpResponse = new SignUpResponse( id, username, enrolledAt );

        return signUpResponse;
    }
}

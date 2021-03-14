package io.todak.bookooromi.account;

import io.todak.bookooromi.account.dto.SignUpRequest;
import io.todak.bookooromi.account.dto.SignUpResponse;
import io.todak.bookooromi.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(SignUpRequest signUpRequest);

    @Mapping(target = "enrolledAt", source = "createdDateTime")
    SignUpResponse toSignUpResponse(Account account);


}

package io.todak.bookooromi.account;

import io.todak.bookooromi.account.dto.SignUpRequest;
import io.todak.bookooromi.account.dto.SignUpResponse;
import io.todak.bookooromi.common.entities.network.Response;
import io.todak.bookooromi.domain.Account;
import io.todak.bookooromi.security.SignInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {
        Account account = AccountMapper.INSTANCE.toEntity(request);

        Account savedAccount = accountService.signUp(account);

        SignUpResponse response = AccountMapper.INSTANCE.toSignUpResponse(savedAccount);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.builder().data(response).build());
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyInfo(@SignInUser Account account) {

        return null;
    }


}

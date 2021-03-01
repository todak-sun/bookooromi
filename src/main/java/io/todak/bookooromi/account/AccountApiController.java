package io.todak.bookooromi.account;

import io.todak.bookooromi.account.dto.SignUpRequest;
import io.todak.bookooromi.account.dto.SignUpResponse;
import io.todak.bookooromi.common.entities.network.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {
        Account account = AccountMapper.INSTANCE.toEntity(request);

        Account savedAccount = accountService.signUp(account);

        SignUpResponse response = AccountMapper.INSTANCE.toSignUpResponse(savedAccount);
        return Response.created(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo() {

        return null;
    }


}

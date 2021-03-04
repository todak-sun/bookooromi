package io.todak.bookooromi.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String password;
    private String passwordRe;

    @Builder
    public SignUpRequest(String username, String password, String passwordRe) {
        this.username = username;
        this.password = password;
        this.passwordRe = passwordRe;
    }
}

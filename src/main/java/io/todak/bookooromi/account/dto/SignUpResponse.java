package io.todak.bookooromi.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SignUpResponse {
    private Long id;
    private String username;
    private LocalDateTime enrolledAt;
}

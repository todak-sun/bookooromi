package io.todak.bookooromi.common.entities.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Response<T> {
    private T data;
    @Builder.Default
    private LocalDateTime transactionTime = LocalDateTime.now();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @Builder
    public Response(T data, String message) {
        this.data = data;
        this.message = message;
    }
}

package io.todak.bookooromi.common.entities.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class Response<T> {
    private T data;
    private LocalDateTime transactionTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private Response(T data) {
        this.data = data;
        this.transactionTime = LocalDateTime.now();
    }

    private Response(T data, String message) {
        this(data);
        this.message = message;
        this.transactionTime = LocalDateTime.now();
    }

    public static <T> ResponseEntity<Response<T>> ok(T data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(data));
    }

    public static <T> ResponseEntity<Response<T>> ok(T data, String message) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(data, message));
    }

    public static <T> ResponseEntity<Response<T>> created(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(data, message));
    }

    public static <T> ResponseEntity<Response<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>(data));
    }

}

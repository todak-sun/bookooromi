package io.todak.bookooromi.book;

import io.todak.bookooromi.book.dto.BookDto;
import io.todak.bookooromi.common.entities.network.Response;
import io.todak.bookooromi.domain.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BookDto.Request.Create request, Errors errors) {

        if (errors.hasErrors()) {
            throw new IllegalArgumentException();
        }

        Book book = BookMapper.INSTANCE.toEntity(request);
        Book savedBook = bookService.create(book);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.builder()
                        .data(BookMapper.INSTANCE.toBookResponseCreate(savedBook))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> fetchAll() {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> update() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return null;
    }

}

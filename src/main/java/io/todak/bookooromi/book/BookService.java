package io.todak.bookooromi.book;

import io.todak.bookooromi.domain.Book;
import io.todak.bookooromi.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book create(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }


}

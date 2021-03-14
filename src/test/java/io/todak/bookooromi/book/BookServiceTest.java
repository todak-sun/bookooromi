package io.todak.bookooromi.book;

import io.todak.bookooromi.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @DisplayName("책 하나를 생성한다.")
    @Test
    public void create_test() {
        //given
        Book book = Book.builder()
                .author("저자")
                .description("설명")
                .isbn("123458128")
                .title("제목")
                .publisher("발행처")
                .category("문학")
                .build();

        Book savedBook = bookService.create(book);

        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getCategory(), savedBook.getCategory());
        assertEquals(book.getPublisher(), savedBook.getPublisher());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getIsbn(), savedBook.getIsbn());
        assertEquals(book.getDescription(), savedBook.getDescription());
        assertNotNull(savedBook.getId());


    }

}
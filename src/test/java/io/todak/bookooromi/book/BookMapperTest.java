package io.todak.bookooromi.book;

import io.todak.bookooromi.book.dto.BookDto;
import io.todak.bookooromi.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles("test")
class BookMapperTest {

    @Test
    @DisplayName("BookDto.Request.Create -> toEntity 테스트")
    public void to_entity() {

        //given
        BookDto.Request.Create create = new BookDto.Request.Create();
        create.setAuthor("author");
        create.setCategory("category");
        create.setDescription("description");
        create.setPublisher("publisher");
        create.setIsbn("isbn");
        create.setTitle("title");

        //when
        Book book = BookMapper.INSTANCE.toEntity(create);

        //then
        assertEquals(book.getAuthor(), create.getAuthor());
        assertEquals(book.getCategory(), create.getCategory());
        assertEquals(book.getDescription(), create.getDescription());
        assertEquals(book.getPublisher(), create.getPublisher());
        assertEquals(book.getIsbn(), create.getIsbn());
        assertEquals(book.getTitle(), create.getTitle());
        assertNull(book.getId());

    }

}
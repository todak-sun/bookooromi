package io.todak.bookooromi.book;

import io.todak.bookooromi.book.dto.BookDto;
import io.todak.bookooromi.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(BookDto.Request.Create request);

    BookDto.Response.Create toBookResponseCreate(Book book);

}

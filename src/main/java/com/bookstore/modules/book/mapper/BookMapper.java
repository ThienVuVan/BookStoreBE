package com.bookstore.modules.book.mapper;

import com.bookstore.common.entity.Book;
import com.bookstore.modules.book.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book BookDtoToBook(BookDto bookDto);
    BookDto BookToBookDto(Book book);
}

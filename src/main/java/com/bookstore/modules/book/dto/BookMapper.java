package com.bookstore.modules.book.dto;

import com.bookstore.common.dto.BookDto;
import com.bookstore.common.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book BookDtoToBook(BookDto bookDto);
    BookDto BookToBookDto(Book book);
}

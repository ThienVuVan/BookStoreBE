package com.bookstore.modules.book.mapper;

import com.bookstore.common.entity.Book;
import com.bookstore.modules.book.dto.BookDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto BookToBookDto(Book book);
}

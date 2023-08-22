package com.bookstore.modules.book.mapper;

import com.bookstore.common.entity.Author;
import com.bookstore.modules.book.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto AuthorToDto(Author author);
}

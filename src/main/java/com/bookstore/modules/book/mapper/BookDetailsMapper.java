package com.bookstore.modules.book.mapper;

import com.bookstore.common.entity.BookDetails;
import com.bookstore.modules.book.dto.BookDetailsDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BookDetailsMapper {
    BookDetailsDto BookDetailsToDto(BookDetails bookDetails);
}

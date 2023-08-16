package com.bookstore.modules.book.mapper;

import com.bookstore.common.entity.BookImage;
import com.bookstore.modules.book.dto.BookImageDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface BookImageMapper {
    List<BookImageDto> bookImageToDto(List<BookImage> bookImages);
}

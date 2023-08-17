package com.bookstore.modules.book.service;

import com.bookstore.common.entity.Book;
import com.bookstore.modules.book.dto.BookDto;
import com.bookstore.modules.book.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookModuleService {
    private final BookMapper bookMapper;

    public BookDto convertToBookDto(Book book){
        return bookMapper.BookToBookDto(book);
    }

    public List<BookDto> convertToListBookDto(List<Book> books){
        return books.stream().map(book -> bookMapper.BookToBookDto(book)).collect(Collectors.toList());
    }
}

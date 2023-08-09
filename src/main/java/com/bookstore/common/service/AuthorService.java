package com.bookstore.common.service;

import com.bookstore.common.entity.Author;
import java.util.List;

public interface AuthorService {
    public List<Author> retrieveAllAuthor();
    public Author saveAuthor(Author author);
    public Author updateAuthor(Author author);
    public void deleteAuthor(Author author);
}

package com.bookstore.common.repository;

import com.bookstore.common.entity.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImage, Integer> {
    BookImage findBookImageById(Integer id);
}

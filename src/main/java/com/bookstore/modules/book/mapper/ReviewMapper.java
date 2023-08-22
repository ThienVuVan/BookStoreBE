package com.bookstore.modules.book.mapper;

import com.bookstore.common.entity.Review;
import com.bookstore.modules.book.dto.ReviewDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto ReviewToDto(Review review);
}

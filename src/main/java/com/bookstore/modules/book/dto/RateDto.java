package com.bookstore.modules.book.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RateDto {
    Integer fiveStar;
    Integer fourStar;
    Integer threeStar;
    Integer twoStar;
    Integer oneStar;
}

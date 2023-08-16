package com.bookstore.modules.book.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RateResponse {
    Integer fiveStar;
    Integer fourStar;
    Integer threeStar;
    Integer twoStar;
    Integer oneStar;
}

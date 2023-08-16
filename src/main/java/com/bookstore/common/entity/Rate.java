package com.bookstore.common.entity;

import com.bookstore.common.entity.compositekey.UserBookKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "rates")
public class Rate extends Common {
    @EmbeddedId
    UserBookKey id;

    /* Delete Rate, Does Not Delete User */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    /* Delete Rate, Does Not Delete Book */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    Book book;

    @NotNull
    @PositiveOrZero
    @Range(max = 5, min = 0)
    @Column(name = "rating")
    Integer rating;

    /* <------------- Entity Method --------------> */

    public Rate(UserBookKey id, User user, Book book, Integer rating) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", rating=" + rating +
                '}';
    }
}

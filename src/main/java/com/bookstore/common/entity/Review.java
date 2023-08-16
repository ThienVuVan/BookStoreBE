package com.bookstore.common.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "reviews")
public class Review extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    /* Delete Review, Does Not Delete User */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    User user;

    /* Delete Review, Does Not Delete Book */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    Book book;

    @NotBlank
    @Length(min = 1)
    @Column(name = "comment")
    String comment;

    @Lob
    @Column(name = "image")
    String image;

    /* <------------ Entity Method -------------> */

    public Review(User user, Book book, String comment) {
        this.user = user;
        this.book = book;
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", comment='" + comment + '\'' +
                '}';
    }
}

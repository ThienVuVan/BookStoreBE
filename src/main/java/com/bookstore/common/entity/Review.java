package com.bookstore.common.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* Delete Review, Does Not Delete User */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    /* Delete Review, Does Not Delete Book */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "comment")
    @Length(max = 255, min = 1)
    @NotBlank
    private String comment;

    @Column(name = "image")
    @Lob
    private Byte[] image;

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

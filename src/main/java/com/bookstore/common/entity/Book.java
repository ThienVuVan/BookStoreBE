package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "books")
public class Book extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Length(min = 1)
    @Column(name = "title")
    String title;

    @NotNull
    @PositiveOrZero
    @Column(name = "price")
    Double price;

    @NotBlank
    @Length(min = 2)
    @Column(name = "author")
    String author;

    @NotNull
    @Range(max = 10000, min = 0)
    @Column(name = "current_quantity")
    Integer currentQuantity;

    @Range(max = 10000, min = 0)
    @Column(name = "sold_quantity")
    Integer soldQuantity;

    /* <-------------- Method for Entity --------------> */


    public Book(String title, @NotNull Double price, @NotNull Integer currentQuantity, @NotNull Integer soldQuantity) {
        this.title = title;
        this.price = price;
        this.currentQuantity = currentQuantity;
        this.soldQuantity = soldQuantity;
    }

    public Book(String title, @NotNull Double price, String author, @NotNull Integer currentQuantity) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.currentQuantity = currentQuantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", currentQuantity=" + currentQuantity +
                ", soldQuantity=" + soldQuantity +
                '}';
    }

    /* <-------------- Convenience Method ---------------> */

    public void addCategory(Category category){
        if(categories == null) categories = new HashSet<>();
        categories.add(category);
        category.getBooks().add(this);
    }

    public void addBookImage(BookImage bookImage){
        if(bookImages == null) bookImages = new HashSet<>();
        bookImages.add(bookImage);
        bookImage.setBook(this);
    }

    /* <-------------- Mapping ----------------> */

    /* To Shop */
    /* Delete Book, Does Not Delete Shop */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    Shop shop;

    /* To BookDetails */
    /* Delete Book, Delete BookDetails */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_details_id")
    BookDetails bookDetails;


    /* To BookImage */
    /* Delete Book, Delete BookImage */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    Set<BookImage> bookImages;

    /* To User */
    /* Delete Book, Does Not Delete User */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    /* To Review */
    /* Delete Book, Delete Reviews */
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    Set<Review> reviews;

    /* To Rate */
    /* Delete Book, Delete Rate */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    Set<Rate> rates;

    /* To BookCategory */
    /* Delete Book, Delete books_categories */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories;

    /* To OrderItem */
    /* Delete Book, Delete Order */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.PERSIST)
    Set<OrderItem> orderItems;
}

package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "price")
    @PositiveOrZero
    private Double price;

    @Column(name = "current_quantity")
    @NotNull
    @Range(max = 10000, min = 0)
    private Integer currentQuantity;

    @Column(name = "sold_quantity")
    @NotNull
    @Range(max = 10000, min = 0)
    private Integer soldQuantity;

    /* <-------------- Method for Entity --------------> */

    public Book(String title, @NotNull Double price, @NotNull Integer currentQuantity, @NotNull Integer soldQuantity) {
        this.title = title;
        this.price = price;
        this.currentQuantity = currentQuantity;
        this.soldQuantity = soldQuantity;
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

    public void addAuthor(Author author){
        if(authors == null) authors = new HashSet<>();
        authors.add(author);
        author.getBooks().add(this);
    }

    public void addCategory(Category category){
        if(categories == null) categories = new HashSet<>();
        categories.add(category);
        category.getBooks().add(this);
    }

    /* <-------------- Mapping ----------------> */

    /* To BookDetails */
    /* Delete Book, Delete BookDetails */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_details_id")
    private BookDetails bookDetails;


    /* To BookImage */
    /* Delete Book, Delete BookImage */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookImage> bookImages;

    /* To User */
    /* Delete Book, Does Not Delete User */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /* To Review */
    /* Delete Book, Delete Reviews */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    /* To Rate */
    /* Delete Book, Delete Rate */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Rate> rates;

    /* To BookCategory */
    /* Delete Book, Delete Category, Update Later */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    /* To BookAuthor */
    /* Delete Book, Delete Author */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    /* To OrderItem */
    /* Delete Book, Delete Order */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
}

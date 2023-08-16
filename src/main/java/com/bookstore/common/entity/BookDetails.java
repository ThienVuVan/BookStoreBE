package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "book_details")
public class BookDetails extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "publisher")
    String publisher;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
    LocalDate publicationDate;

    @Column(name = "dimension")
    String Dimension;

    @Column(name = "cover_type")
    String coverType;

    @Column(name = "number_of_pages")
    Integer numberOfPages;

    @Column(name = "publishing_house")
    String publishingHouse;

    @Lob
    @Column(name = "description")
    String description;

    /* <------------------ Entity Method -------------------> */


    /* <------------------ Mapping --------------------> */

    /* To Book */
    /* Delete BookDetails, Does Not Delete Book */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bookDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    Book book;
}

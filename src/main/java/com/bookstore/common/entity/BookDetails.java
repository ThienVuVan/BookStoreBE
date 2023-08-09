package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "book_details")
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "publisher")
    private String publisher;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "dimension")
    private String Dimension;

    @Column(name = "cover_type")
    private String coverType;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @Lob
    @Column(name = "description")
    private String description;

    /* <------------------ Entity Method -------------------> */


    /* <------------------ Mapping --------------------> */

    /* To Book */
    /* Delete BookDetails, Does Not Delete Book */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bookDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Book book;
}

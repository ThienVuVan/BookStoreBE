package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "authors")
public class Author extends Common{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Length(min = 2)
    @Column(name = "name")
    String name;

    @Email
    @Column(name = "email", nullable = true)
    String email;

    /* <-------------- Entity Method -----------------> */

    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /* <-------------- Mapping ---------------> */

    /* To BookAuthor */
    /* Delete Author, Delete Book */
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    Set<Book> books = new HashSet<>();
}

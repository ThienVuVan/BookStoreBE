package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Length(min = 2)
    @Column(name = "name")
    private String name;

    /* <------------ Entity Method -------------> */

    public Category(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /* <------------ Mapping -------------> */

    /* To BookCategory */
    /* Delete Category, Delete Book, Update Later */
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Book> books = new HashSet<>();
}

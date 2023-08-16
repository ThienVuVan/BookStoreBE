package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "categories")
public class Category extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Length(min = 2)
    @Column(name = "name")
    String name;

    @Column(name = "parent_id")
    Integer parentId;

    /* <------------ Entity Method -------------> */

    public Category(String name, Integer parentId) {
        this.name = name;
        this.parentId = parentId;
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
    Set<Book> books = new HashSet<>();
}

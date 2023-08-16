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
@Table(name = "roles")
public class Role extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Length(min = 2)
    @Column(name = "name", unique = true)
    String name;

    public Role(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /* <------------ Mapping -----------> */

    /* To User */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    /* <--------------------- Convenience Method ----------------------> */

    public void addUser(User user){
        if(users == null) users = new HashSet<>();
        users.add(user);
        user.getRoles().add(this);
    }

}

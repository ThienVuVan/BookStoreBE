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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    @NotBlank
    @Length(max = 50)
    private String name;

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
    private Set<User> users;

    /* <--------------------- Convenience Method ----------------------> */

    public void addUser(User user){
        if(users == null) users = new HashSet<>();
        users.add(user);
        user.getRoles().add(this);
    }

}

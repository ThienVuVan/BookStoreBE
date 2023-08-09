package com.bookstore.common.entity;

import com.bookstore.common.annotation.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", unique = true)
    @NotBlank
    @Length(max = 50, min = 5)
    private String username;

    @Column(name = "phone_number", unique = true)
    @Phone
    private String phoneNumber;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "password")
    @NotBlank
    @Length(max = 1000, min = 8)
    private String password;

    /* <-------------- Method for Entity --------------> */

    public User(String username, String phoneNumber, String email, String password) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /* <----------------- Convenience Method -----------------> */

    public void addRole(Role role){
        if(roles == null) roles = new HashSet<>();
        roles.add(role);
        role.getUsers().add(this);
    }
    public void addBook(Book book){
        if(books == null) books = new HashSet<>();
        books.add(book);
        book.setUser(this);
    }
    public void addOrder(Order order){
        if(orders == null) orders = new HashSet<>();
        orders.add(order);
        order.setUser(this);
    }

    /* <------------- Mapping -------------> */

    /* To Role */
    /* Delete User, Does Not Delete Role */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /* To Book */
    /* Delete User, Delete All Book Belong To User */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Book> books;

    /* To Shop */
    /* Delete User, Delete Shop */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    /* To Review */
    /* Delete User, Delete Review */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    /* To Rate */
    /* Delete User, Delete Rate */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Rate> rates;

    /* To Order */
    /* Delete User, Delete Order */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders;
}

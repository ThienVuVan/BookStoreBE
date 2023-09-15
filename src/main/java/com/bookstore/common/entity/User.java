package com.bookstore.common.entity;

import com.bookstore.common.annotation.Phone;
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
@Table(name = "users")
public class User extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Length(max = 50, min = 5)
    @Column(name = "user_name", unique = true)
    String username;

    @Phone(message = "must be a well-formed phone number")
    @Column(name = "phone_number")
    String phoneNumber;

    @Email
    @Column(name = "email")
    String email;

    @NotBlank
    @Length(min = 8)
    @Column(name = "password")
    String password;

    @Column(name = "type")
    String type;

    @Column(name = "social_id")
    String socialId;

    /* <-------------- Method for Entity --------------> */

    public User(String username, String phoneNumber, String email, String password, String type, String socialId) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.type = type;
        this.socialId = socialId;
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
    public void addShop(Shop shop){
        setShop(shop);
        shop.setUser(this);
    }
    public void addReview(Review review){
        if(reviews == null) reviews = new HashSet<>();
        reviews.add(review);
    }

    /* <------------- Mapping -------------> */

    /* To Role */
    /* Delete User, Does Not Delete Role */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    /* To Book */
    /* Delete User, Delete All Book Belong To User */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    Set<Book> books;

    /* To Shop */
    /* Delete User, Delete Shop */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    Shop shop;

    /* To Review */
    /* Delete User, Delete Review */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    Set<Review> reviews;

    /* To Rate */
    /* Delete User, Delete Rate */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    Set<Rate> rates;

    /* To Order */
    /* Delete User, Delete Order */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    Set<Order> orders;
}

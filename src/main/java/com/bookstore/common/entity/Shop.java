package com.bookstore.common.entity;

import com.bookstore.common.annotation.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "shops")
public class Shop extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @Lob
    @Column(name = "shop_logo_path")
    String shopLogoPath;

    @NotBlank
    @Length(min = 2)
    @Column(name = "shop_name")
    String shopName;

    @Column(name = "shop_address")
    String shopAddress;

    @Phone
    @Column(name = "contact_phone")
    String contactPhone;

    @Email
    @Column(name = "contact_email")
    String contactEmail;

    /* <------------------ Entity Method -------------------> */


    /* <------------------ Mapping --------------------> */

    /* To Book */
    /* Delete Shop, Delete Books */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    Set<Book> books;

    /* To ShopDetails */
    /* Delete Shop, Delete ShopDetails */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_details_id")
    ShopDetails shopDetails;

    /* To Order */
    /* Delete Shop, Delete Order */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    Set<Order> orders;

    /* To User */
    /* Delete Shop, Does Not Delete User */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    User user;
}

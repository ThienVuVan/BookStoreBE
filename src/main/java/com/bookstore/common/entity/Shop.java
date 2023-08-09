package com.bookstore.common.entity;

import com.bookstore.common.annotation.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "shop_logo")
    @NotNull
    @Lob
    private Byte[] shopLogo;

    @Column(name = "shop_name")
    @NotBlank
    @Length(max = 50, min = 5)
    private String shopName;

    @Column(name = "shop_address")
    private String shopAddress;

    @Column(name = "contact_phone")
    @Phone
    private String contactPhone;

    @Column(name = "contact_email")
    @Email
    private String contactEmail;

    /* <------------------ Entity Method -------------------> */


    /* <------------------ Mapping --------------------> */

    /* To ShopDetails */
    /* Delete Shop, Delete ShopDetails */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_details_id")
    private ShopDetails shopDetails;

    /* To Order */
    /* Delete Shop, Delete Order */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<Order> orders;

    /* To User */
    /* Delete Shop, Does Not Delete User */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shop", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private User user;
}

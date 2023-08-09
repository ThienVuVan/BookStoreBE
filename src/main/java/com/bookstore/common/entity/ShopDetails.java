package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "shop_details")
public class ShopDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "operating_hours")
    private String operationHours;

    @Column(name = "shipping_policy")
    private String shippingPolicy;

    @Column(name = "return_policy")
    private String returnPolicy;

    /* <------------------ Entity Method -------------------> */


    /* <------------------ Mapping --------------------> */

    /* To Shop */
    /* Delete ShopDetails, Does Not Delete Shop */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shopDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Shop shop;
}

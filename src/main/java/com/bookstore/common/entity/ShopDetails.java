package com.bookstore.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "shop_details")
public class ShopDetails extends Common {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "description")
    String description;

    @Column(name = "operating_hours")
    String operationHours;

    @Column(name = "shipping_policy")
    String shippingPolicy;

    @Column(name = "return_policy")
    String returnPolicy;

    /* <------------------ Entity Method -------------------> */


    /* <------------------ Mapping --------------------> */

    /* To Shop */
    /* Delete ShopDetails, Does Not Delete Shop */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "shopDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    Shop shop;
}

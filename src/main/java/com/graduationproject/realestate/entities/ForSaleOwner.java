package com.graduationproject.realestate.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "forsaleowner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForSaleOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate listingDate;

    private String advertTitle;

    private Long price;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String numberOfRooms;

    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    public ForSaleOwner(LocalDate listingDate, String advertTitle, Long price, ProductType productType, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished, Owner owner, City city) {
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.productType = productType;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
        this.owner = owner;
        this.city = city;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
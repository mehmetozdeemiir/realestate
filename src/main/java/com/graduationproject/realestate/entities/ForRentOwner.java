package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.ForRentOwnerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "forrentowner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForRentOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate listingDate;

    private String advertTitle;

    private Long price;

    @Enumerated(EnumType.STRING)
    private ImmovablesTypes immovablesTypes;

    private String numberOfRooms;

    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    public ForRentOwner( LocalDate listingDate, String advertTitle, Long price, ImmovablesTypes immovablesTypes, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished, Owner owner, City city) {
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.immovablesTypes = immovablesTypes;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
        this.owner = owner;
        this.city = city;
    }
    public static ForRentOwner from(ForRentOwnerRequest ownerRentRequest) {
        return ForRentOwner.builder()
                .listingDate(ownerRentRequest.getListingDate())
                .advertTitle(ownerRentRequest.getAdvertTitle())
                .price(ownerRentRequest.getPrice())
                .immovablesTypes(ownerRentRequest.getImmovablesTypes())
                .numberOfRooms(ownerRentRequest.getNumberOfRooms())
                .buildingAge(ownerRentRequest.getBuildingAge())
                .balcony(ownerRentRequest.getBalcony())
                .furnished(ownerRentRequest.getFurnished())
                .build();
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}

package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRentOwner;
import com.graduationproject.realestate.entities.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForRentOwnerResponse {
    private LocalDate listingDate;
    private String advertTitle;
    private Long price;
    private ProductType productType;
    private String numberOfRooms;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private String district;
    private String cityName;
    private Long ownerId;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public static ForRentOwnerResponse from(ForRentOwner forRent) {

       return ForRentOwnerResponse.builder()
                .listingDate(forRent.getListingDate())
                .advertTitle(forRent.getAdvertTitle())
                .price(forRent.getPrice())
                .productType(forRent.getProductType())
                .numberOfRooms(forRent.getNumberOfRooms())
                .buildingAge(forRent.getBuildingAge())
                .balcony(forRent.getBalcony())
                .furnished(forRent.getFurnished())
                .district(forRent.getCity().getDistrict())
                .cityName(forRent.getCity().getCityName())
                .ownerId(forRent.getOwner().getId())
                .firstName(forRent.getOwner().getFirstName())
                .lastName(forRent.getOwner().getLastName())
                .build();
    }
}
package com.graduationproject.realestate.response;
import com.graduationproject.realestate.entities.ForSaleOwner;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForSaleOwnerResponse {
    private LocalDate listingDate;
    private String advertTitle;
    private Long price;
    private ImmovablesTypes immovablesTypes;
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

    public static ForSaleOwnerResponse from(ForSaleOwner forSale) {

        return ForSaleOwnerResponse.builder()
                .listingDate(forSale.getListingDate())
                .advertTitle(forSale.getAdvertTitle())
                .price(forSale.getPrice())
                .immovablesTypes(forSale.getImmovablesTypes())
                .numberOfRooms(forSale.getNumberOfRooms())
                .buildingAge(forSale.getBuildingAge())
                .balcony(forSale.getBalcony())
                .furnished(forSale.getFurnished())
                .district(forSale.getCity().getDistrict())
                .cityName(forSale.getCity().getCityName())
                .ownerId(forSale.getOwner().getId())
                .firstName(forSale.getOwner().getFirstName())
                .lastName(forSale.getOwner().getLastName())
                .contactNumber(forSale.getOwner().getContactNumber())
                .build();
    }

}

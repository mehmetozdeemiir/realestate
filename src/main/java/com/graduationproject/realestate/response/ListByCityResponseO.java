package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRentOwner;
import com.graduationproject.realestate.entities.ForSaleOwner;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListByCityResponseO {
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
    private Long cityId;
    private String firstName;
    private String lastName;

    public static ListByCityResponseO from(ForSaleOwner forCity){
        return ListByCityResponseO.builder()
                .listingDate(forCity.getListingDate())
                .advertTitle(forCity.getAdvertTitle())
                .price(forCity.getPrice())
                .immovablesTypes(forCity.getImmovablesTypes())
                .numberOfRooms(forCity.getNumberOfRooms())
                .buildingAge(forCity.getBuildingAge())
                .balcony(forCity.getBalcony())
                .furnished(forCity.getFurnished())
                .cityId(forCity.getCity().getId())
                .district(forCity.getCity().getDistrict())
                .cityName(forCity.getCity().getCityName())
                .firstName(forCity.getOwner().getFirstName())
                .lastName(forCity.getOwner().getLastName())
                .build();

    }
    public static ListByCityResponseO froms(ForRentOwner forCity){
        return ListByCityResponseO.builder()
                .listingDate(forCity.getListingDate())
                .advertTitle(forCity.getAdvertTitle())
                .price(forCity.getPrice())
                .immovablesTypes(forCity.getImmovablesTypes())
                .numberOfRooms(forCity.getNumberOfRooms())
                .buildingAge(forCity.getBuildingAge())
                .balcony(forCity.getBalcony())
                .furnished(forCity.getFurnished())
                .cityId(forCity.getCity().getId())
                .district(forCity.getCity().getDistrict())
                .cityName(forCity.getCity().getCityName())
                .firstName(forCity.getOwner().getFirstName())
                .lastName(forCity.getOwner().getLastName())
                .build();

    }

}

package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRentEstateAgent;
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
public class ForRentCityResponse {
    private LocalDate listingDate;
    private String advertTitle;
    private Long price;
    private ImmovablesTypes immovablesTypes;
    private String numberOfRooms;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private Long cityId;
    private String district;
    private String cityName;

    public static ForRentCityResponse from(ForRentEstateAgent forRent){
        return ForRentCityResponse.builder()
                .listingDate(forRent.getListingDate())
                .advertTitle(forRent.getAdvertTitle())
                .price(forRent.getPrice())
                .immovablesTypes(forRent.getImmovablesTypes())
                .numberOfRooms(forRent.getNumberOfRooms())
                .buildingAge(forRent.getBuildingAge())
                .balcony(forRent.getBalcony())
                .furnished(forRent.getFurnished())
                .cityId(forRent.getCity().getId())
                .district(forRent.getCity().getDistrict())
                .cityName(forRent.getCity().getCityName())
                .build();
    }


}

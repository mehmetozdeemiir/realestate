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
public class ForRentEstateAgentResponse {
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
    private Long estateAgentId;
    private String companyName;
    private String contactNumber;

    public static ForRentEstateAgentResponse from(ForRentEstateAgent forRent){
        return ForRentEstateAgentResponse.builder()
                .listingDate(forRent.getListingDate())
                .advertTitle(forRent.getAdvertTitle())
                .price(forRent.getPrice())
                .immovablesTypes(forRent.getImmovablesTypes())
                .numberOfRooms(forRent.getNumberOfRooms())
                .buildingAge(forRent.getBuildingAge())
                .balcony(forRent.getBalcony())
                .furnished(forRent.getFurnished())
                .district(forRent.getCity().getDistrict())
                .cityName(forRent.getCity().getCityName())
                .estateAgentId(forRent.getEstateAgent().getId())
                .companyName(forRent.getEstateAgent().getCompanyName())
                .contactNumber(forRent.getEstateAgent().getContactNumber())
                .build();
    }


}
